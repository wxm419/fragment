package com.fheebiy.activity.lite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.adapter.LiteHttpAdapter;
import com.fheebiy.http.lite.HttpHelper.HttpHelper;
import com.fheebiy.model.LiteHttpUrl;
import com.fheebiy.model.News;
import com.fheebiy.model.NewsDetail;
import com.fheebiy.util.Log;
import com.fheebiy.http.lite.LiteHttpClient;
import com.fheebiy.http.lite.async.HttpAsyncExecutor;
import com.fheebiy.http.lite.exception.HttpException;
import com.fheebiy.http.lite.request.Request;
import com.fheebiy.http.lite.request.param.HttpMethod;
import com.fheebiy.http.lite.response.Response;
import com.fheebiy.http.lite.response.handler.HttpModelHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-12-24.
 *
 * lite http请求，测试，目前感觉，还是比较简单
 */
public class LiteHttpActivity extends Activity {

    private ListView listView;

    private LiteHttpAdapter adapter;

    private LiteHttpClient client;

    private HttpAsyncExecutor asyncExecutor;

    public static final String TAG = "LiteHttpActivity";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lite_act);
        listView = (ListView) findViewById(R.id.listView);
        adapter = new LiteHttpAdapter(this);
        listView.setAdapter(adapter);
        adapter.setList(getDataList());
      /*  adapter.notifyDataSetChanged();*/
        client = LiteHttpClient.newApacheHttpClient(this);          //不自带一部执行器，需要自行new Thread
        asyncExecutor = HttpAsyncExecutor.newInstance(client);      //自带异步执行器
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               final LiteHttpUrl url = (LiteHttpUrl) adapter.getItem(position);
                if(position == 0){  //home page
                    innerAsyncGetModel(url.getUrl());
                }

                if(position == 1){  //search result

                }

                if(position == 2){  //bar.mblog
                  new Thread(new Runnable() {
                      @Override
                      public void run() {
                          makeBasicGetRequest(url.getUrl());
                      }
                  }).start();
                }
            }
        });
    }

    //带参数的http请求封装，目前看来，还可以，比较简单，扩展性也还行
    public void innerAsyncGetModel(String url) {
        String sy_c = "1IuI%2FIp6%2BxenXcu4WL5YCfCH2y7S6FR6Og%2FPcq%2FsMA%2FQiUJr%2FGF6BMkOtbPgheTjb0s%2B1noXns2lsbwPvr%2FNbEPmsQKd8H1Pu%2BG1GF20aSYeHDbi4tPZ5DT5nLkkdSuVys75J7OD8pqABLeONit197om52%2B3Aq36O%2Bmpudl6OrBc7F7bvdGZJWcB44uw%2BbuHewzuQ2FXZx3BDW5imSUbtCKI14C3DKLEyEI2NuXrFsJjarZBpkaUKLxDYtfaPyiXicvDXLIREpxxgKh43mKkEptDg59FDGi8RLd6kz6CjuevZjXyx3lcyUhBcx71r5TXn6g5IojJz0V3A%2BgWG9qe74hR2dKOn6on9qkuWw9IpcdO";
        asyncExecutor.execute(HttpHelper.getNewsReq(sy_c), new HttpModelHandler<News>() {
            @Override
            protected void onSuccess(News data, Response res) {
                Log.d(TAG, "XX");
                List<NewsDetail> list = data.body;
                List<LiteHttpUrl> urlList = new ArrayList<LiteHttpUrl>();
                for(NewsDetail detail: list){
                    LiteHttpUrl url = new LiteHttpUrl();
                    url.setName(detail.getTitle());
                    url.setUrl(detail.getDescrption());
                    urlList.add(url);
                }

                adapter.addList(urlList);
            }

            @Override
            protected void onFailure(HttpException e, Response res) {
                Log.d(TAG, "XX");
                toast("error"+e.getMessage());
            }
        });
    }


    public void makeBasicGetRequest(String url){
        Request req = new Request(url).setMethod(HttpMethod.Get);
        Response res = client.execute(req);
        Log.d(TAG, "XX");
    }





    public List<LiteHttpUrl> getDataList() {
        List<LiteHttpUrl> list = new ArrayList<LiteHttpUrl>();
        LiteHttpUrl httpUrl = new LiteHttpUrl();
        httpUrl.setName("home page");
        httpUrl.setUrl("http://api2.souyue.mobi/d3api2/webdata/homepage.news.groovy?vc=4.0.6&sy_c=1IuI%2FIp6%2BxenXcu4WL5YCfCH2y7S6FR6Og%2FPcq%2FsMA%2FQiUJr%2FGF6BMkOtbPgheTjb0s%2B1noXns2lsbwPvr%2FNbEPmsQKd8H1Pu%2BG1GF20aSYeHDbi4tPZ5DT5nLkkdSuVys75J7OD8pqABLeONit197om52%2B3Aq36O%2Bmpudl6OrBc7F7bvdGZJWcB44uw%2BbuHewzuQ2FXZx3BDW5imSUbtCKI14C3DKLEyEI2NuXrFsJjarZBpkaUKLxDYtfaPyiXicvDXLIREpxxgKh43mKkEptDg59FDGi8RLd6kz6CjuevZjXyx3lcyUhBcx71r5TXn6g5IojJz0V3A%2BgWG9qe74hR2dKOn6on9qkuWw9IpcdO");
        list.add(httpUrl);

        LiteHttpUrl url = new LiteHttpUrl();
        url.setName("search result");
        url.setUrl("http://api2.souyue.mobi/d3api2/webdata/search.result.groovy?vc=4.0.6&sy_c=1GN96d4xbXn1MGwbh8oalr3%2Fx9XcNVDdcWDrEPCsgQdEmAZgADsddKqE6WWgDwyzDKf2ljTNVb61ve2LyR6lMlr2ICFM7hexO33wQWFQxbsS7qgnD5nGirAtK67jQb%2FluKfwiF3NUYnSl7cMqYYazO6J3aEh7j2Akxk0iUExYW0QPMA2JPRZtngeJ3RSf3SlQgSpP9TS6BD4uwk4aNJ8Rgfi82zeAsMQHiI2TilzFiJNph8TLPtz1BWssB69pidosSV2lCAuVS%2BtbiJj6xsJMRM1r4rROfRh2cYiBoTT8B6aYvrFblIPQU9KaoBEDGNk0J79HzsIGWLKWcw2MYgP5S7KZ1dzbMmDtw%3D%3D");
        list.add(url);

        LiteHttpUrl url1 = new LiteHttpUrl();
        url1.setName("bar4.0.mblog");
        url1.setUrl("http://api2.souyue.mobi/d3api2/interest/bar4.0.mblog.list.groovy?vc=4.0.6&sy_c=1UEtT8QTgpm%2BRwln3o48RpXCDZE3I%2B8zi%2FGYX7RCvm4VwObIH%2FsHq9xeWUDxmRSBL4veKhA%2Fn5yRVUlHuC%2B32GTWceyWRsBdgmHURaqHgCwTcDrS%2BewVuUgdRFjHGp1shPp710fa%2Fl1apiWGrSCECHvsBoh686HpCPhT%2Fk4Rj5WcLLLAieirDrr4%2Bsl%2BlU88uopNyc7OnBzONiNCaUvy%2Bl7r0CTikeUuhALUprNlT4f62gWcBjDr2dzVrDEh%2Fpd3NIAXJ699tpvlKP5p%2FvCp%2Fd3VNeIKpjh%2B4fziiN%2BeXgGmM8vuGX2NWfqFPb05eP%2FSUcJkVLbku%2FO8Qq9KJ7CWlacvWYrIW6KJL5qjunpt7wVopmf7js%2FuDW2LusYixEs3jZRMzjShBolp%2Bw2KE%2BuWkEk%3D");
        list.add(url1);

        return list;
    }


    private void toast(String s) {
        Toast.makeText(LiteHttpActivity.this, s, Toast.LENGTH_LONG).show();
    }
}