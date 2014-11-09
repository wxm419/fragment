/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.fheebiy.activity.pulltorefresh;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.pulltorefresh.library.PullToRefreshBase;
import com.fheebiy.pulltorefresh.library.PullToRefreshBase.Mode;
import com.fheebiy.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.fheebiy.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.fheebiy.pulltorefresh.library.PullToRefreshBase.State;
import com.fheebiy.pulltorefresh.library.PullToRefreshListView;
import com.fheebiy.pulltorefresh.library.extras.SoundPullEventListener;
import com.fheebiy.activity.pulltorefresh.support.TranslateAnimation;

import java.util.Arrays;
import java.util.LinkedList;

public final class PullToRefreshListActivity extends ListActivity {

	static final int MENU_MANUAL_REFRESH = 0;
	static final int MENU_DISABLE_SCROLL = 1;
	static final int MENU_SET_MODE = 2;
	static final int MENU_DEMO = 3;

	private LinkedList<String> mListItems;
	private PullToRefreshListView mPullRefreshListView;
	private ArrayAdapter<String> mAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ptr_list);

		titleLayout = findViewById(R.id.title_layout);
		
		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

		// Set a listener to be invoked when the list should be refreshed.
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				String label = DateUtils.formatDateTime(getApplicationContext(), System.currentTimeMillis(),
						DateUtils.FORMAT_SHOW_TIME | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_ABBREV_ALL);

				// Update the LastUpdatedLabel
				refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(label);

				// Do work to refresh the list here.
				new GetDataTask().execute();
			}
		});

		// Add an end-of-list listener
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				Toast.makeText(PullToRefreshListActivity.this, "End of List!", Toast.LENGTH_SHORT).show();
			}
		});

		ListView actualListView = mPullRefreshListView.getRefreshableView();

		// Need to use the Actual ListView when registering for Context Menu
		registerForContextMenu(actualListView);

		mListItems = new LinkedList<String>();
		mListItems.addAll(Arrays.asList(mStrings));

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);

		/**
		 * Add Sound Event Listener
		 */
		SoundPullEventListener<ListView> soundListener = new SoundPullEventListener<ListView>(this);
		soundListener.addSoundEvent(State.PULL_TO_REFRESH, R.raw.pull_event);
		soundListener.addSoundEvent(State.RESET, R.raw.reset_sound);
		soundListener.addSoundEvent(State.REFRESHING, R.raw.refreshing_sound);
		mPullRefreshListView.setOnPullEventListener(soundListener);

		// You can also just use setListAdapter(mAdapter) or
		// mPullRefreshListView.setAdapter(mAdapter)
		actualListView.setAdapter(mAdapter);
		
		// 绑定动画
		setupAnimation();
		mPullRefreshListView.setOnHeaderPullingListener(listener);
		mPullRefreshListView.getRefreshableView().setOnScrollListener(new AbsListView.OnScrollListener() {
			private int firstVisibleItem;
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// 当列表滚动停止，并且当前可见条目不是第一条的话，运行动画。
				if(SCROLL_STATE_IDLE == scrollState && firstVisibleItem > 0){
					long currentTime = System.currentTimeMillis();
					long diff = currentTime - lastDelayTime;
					if(!mallLayoutHasHide && !isAnimationPlaying && diff >= ANIMATIONS_MIN_DELAY){
						forAnimation.startAnimation(hideAnimation);
						lastDelayTime = System.currentTimeMillis();
						mallLayoutHasHide = true;
					}
				}
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
				this.firstVisibleItem = firstVisibleItem;
			}
		});
	}
	
	//////////////////////
	
	static final int ANIMATION_DURATION = 100;
	static final int ANIMATIONS_MIN_DELAY = 1200;
	static final int LAYOUT_HEIGHT = 150;
	
	private View titleLayout;
	private View forAnimation;
	private boolean mallLayoutHasHide = false;
	private boolean isAnimationPlaying = false;
	private TranslateAnimation hideAnimation;
	private TranslateAnimation showAnimation;
	private long lastDelayTime;
	
	private AnimationListener animationListener = new AnimationListener() {
		@Override
		public void onAnimationStart(Animation animation) {
			isAnimationPlaying = true;
		}
		@Override
		public void onAnimationRepeat(Animation animation) { }
		@Override
		public void onAnimationEnd(Animation animation) {
			isAnimationPlaying = false;
		}
	};
	
	private PullToRefreshBase.HeaderPullingListener listener = new PullToRefreshBase.HeaderPullingListener(){
		
		static final int MIN_TIGGER_DISTANCE = 30;
		
		@Override
		public void onHeaderPulling(int orientation, int scrollDistance) {
			//不要一下子执行，当拉伸距离达到一定距离后，再执行自动弹出效果。
			if(Math.abs(scrollDistance) < MIN_TIGGER_DISTANCE) return;
			long currentTime = System.currentTimeMillis();
			long diff = currentTime - lastDelayTime;
			if(mallLayoutHasHide && !isAnimationPlaying && diff >= ANIMATIONS_MIN_DELAY){
				forAnimation.startAnimation(showAnimation);
				lastDelayTime = System.currentTimeMillis();
				mallLayoutHasHide = false;
			}
		}
		
	};
	
	private void setupAnimation(){
		forAnimation = findViewById(R.id.for_animation);
		hideAnimation = new TranslateAnimation(0, 0, 0, -LAYOUT_HEIGHT);
		hideAnimation.setDuration(ANIMATION_DURATION);
		hideAnimation.setInterpolator(new DecelerateInterpolator());
		hideAnimation.setOnTransformListener(new TranslateAnimation.OnTransformListener() {
			@Override
			public void onTransform(float dy) {
				int offset = (int) dy;
				onAnimationTransfrom(offset);
			}
		});
		
		showAnimation = new TranslateAnimation(0, 0, -LAYOUT_HEIGHT, 0);
		showAnimation.setDuration(ANIMATION_DURATION);
		showAnimation.setInterpolator(new DecelerateInterpolator());
		showAnimation.setOnTransformListener(new TranslateAnimation.OnTransformListener() {
			@Override
			public void onTransform(float dy) {
				int offset = (int) dy;
				onAnimationTransfrom(offset);
			}
		});
		
		showAnimation.setAnimationListener(animationListener);
		hideAnimation.setAnimationListener(animationListener);
	}
	
	protected void onAnimationTransfrom(int offset) {
		titleLayout.setPadding(0, offset, 0, 0);
	}
	
	//////////////////////

	private class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return mStrings;
		}

		@Override
		protected void onPostExecute(String[] result) {
			mListItems.addFirst("Added after refresh...");
			mAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_MANUAL_REFRESH, 0, "Manual Refresh");
		menu.add(0, MENU_DISABLE_SCROLL, 1,
				mPullRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
						: "Enable Scrolling while Refreshing");
		menu.add(0, MENU_SET_MODE, 0, mPullRefreshListView.getMode() == Mode.BOTH ? "Change to MODE_PULL_DOWN"
				: "Change to MODE_PULL_BOTH");
		menu.add(0, MENU_DEMO, 0, "Demo");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) menuInfo;

		menu.setHeaderTitle("Item: " + getListView().getItemAtPosition(info.position));
		menu.add("Item 1");
		menu.add("Item 2");
		menu.add("Item 3");
		menu.add("Item 4");

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem disableItem = menu.findItem(MENU_DISABLE_SCROLL);
		disableItem
				.setTitle(mPullRefreshListView.isScrollingWhileRefreshingEnabled() ? "Disable Scrolling while Refreshing"
						: "Enable Scrolling while Refreshing");

		MenuItem setModeItem = menu.findItem(MENU_SET_MODE);
		setModeItem.setTitle(mPullRefreshListView.getMode() == Mode.BOTH ? "Change to MODE_FROM_START"
				: "Change to MODE_PULL_BOTH");

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
			case MENU_MANUAL_REFRESH:
				new GetDataTask().execute();
				mPullRefreshListView.setRefreshing(false);
				break;
			case MENU_DISABLE_SCROLL:
				mPullRefreshListView.setScrollingWhileRefreshingEnabled(!mPullRefreshListView
						.isScrollingWhileRefreshingEnabled());
				break;
			case MENU_SET_MODE:
				mPullRefreshListView.setMode(mPullRefreshListView.getMode() == Mode.BOTH ? Mode.PULL_FROM_START
						: Mode.BOTH);
				break;
			case MENU_DEMO:
				mPullRefreshListView.demo();
				break;
		}

		return super.onOptionsItemSelected(item);
	}

	private String[] mStrings = 
		{ 
			"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
			"Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
			"Allgauer Emmentaler" 
		};
}
