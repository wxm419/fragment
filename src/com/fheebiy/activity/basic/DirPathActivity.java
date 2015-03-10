package com.fheebiy.activity.basic;

import android.app.Activity;
import android.os.Bundle;
import com.fheebiy.R;
import com.fheebiy.util.CommonUtil;
import com.fheebiy.util.Log;

/**
 * Created by bob zhou on 15-3-5.
 * <p/>
 * Android中各种目录，Path的
 *
 * 看Log即可
 */
public class DirPathActivity extends Activity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dirpath);
        init();
    }

    /*
     *通过Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
     *通过Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     *如果使用上面的方法，当你的应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。
     *而且上面二个目录分别对应 设置->应用->应用详情里面的”清除数据“与”清除缓存“选项
     *
     */

    private void init() {
        String path1 = getFilesDir().getPath();
        String path2 = getCacheDir().getPath();

        Log.d(CommonUtil.LOG_TAG, "internal file dir" + path1);   //--->/data/data/com.fheebiy/files
        Log.d(CommonUtil.LOG_TAG, "internal cache dir" + path2);  //--->/data/data/com.fheebiy/cache

        String path3 = getExternalCacheDir().getPath();
        String path4 = getExternalFilesDir(null).getPath();

        Log.d(CommonUtil.LOG_TAG, "sdcard cache dir" + path3);   //--->   /sdcard/Android/data/com.fheebiy/cache
        Log.d(CommonUtil.LOG_TAG, "sdcard file dir" + path4);    //--->   /sdcard/Android/data/com.fheebiy/files

    }
}
