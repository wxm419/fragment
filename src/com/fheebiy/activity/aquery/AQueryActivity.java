package com.fheebiy.activity.aquery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.fheebiy.R;
import com.fheebiy.adapter.AQueryAdapter;
import com.fheebiy.http.aquery.Http;
import com.fheebiy.util.Log;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bob zhou on 14-12-26.
 *
 * AQuery base
 */
public class AQueryActivity extends Activity {


    private AQuery aq;

    private AQueryAdapter adapter;

    private ListView listView;

    private Http http;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_aquery);
        aq = new AQuery(this);
        adapter = new AQueryAdapter(this);
        listView = aq.id(R.id.listView).getListView();
        listView.setAdapter(adapter);
        adapter.setList(getTitles());
        http = new Http(this);
        aq.id(listView).itemClicked(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(AQueryActivity.this, AqImageLoadActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        asyncJson();
                        break;
                    case 2:
                        fileDown();
                        break;
                    case 3:
                        String sy_c = "1IuI%2FIp6%2BxenXcu4WL5YCfCH2y7S6FR6Og%2FPcq%2FsMA%2FQiUJr%2FGF6BMkOtbPgheTjb0s%2B1noXns2lsbwPvr%2FNbEPmsQKd8H1Pu%2BG1GF20aSYeHDbi4tPZ5DT5nLkkdSuVys75J7OD8pqABLeONit197om52%2B3Aq36O%2Bmpudl6OrBc7F7bvdGZJWcB44uw%2BbuHewzuQ2FXZx3BDW5imSUbtCKI14C3DKLEyEI2NuXrFsJjarZBpkaUKLxDYtfaPyiXicvDXLIREpxxgKh43mKkEptDg59FDGi8RLd6kz6CjuevZjXyx3lcyUhBcx71r5TXn6g5IojJz0V3A%2BgWG9qe74hR2dKOn6on9qkuWw9IpcdO";
                        http.getHomePageNews(sy_c);
                        break;
                    default:


                }
            }
        });
    }


    public List<String> getTitles() {
        List<String> list = new ArrayList<String>();
        list.add("图片请求");
        list.add("http");
        list.add("download");
        list.add("callback");
        return list;
    }

    //jsonCallback
    public void asyncJson() {

        //perform a Google search in just a few lines of code

        String url = "http://api2.souyue.mobi/d3api2/webdata/homepage.news.groovy?vc=4.0.6&sy_c=1IuI%2FIp6%2BxenXcu4WL5YCfCH2y7S6FR6Og%2FPcq%2FsMA%2FQiUJr%2FGF6BMkOtbPgheTjb0s%2B1noXns2lsbwPvr%2FNbEPmsQKd8H1Pu%2BG1GF20aSYeHDbi4tPZ5DT5nLkkdSuVys75J7OD8pqABLeONit197om52%2B3Aq36O%2Bmpudl6OrBc7F7bvdGZJWcB44uw%2BbuHewzuQ2FXZx3BDW5imSUbtCKI14C3DKLEyEI2NuXrFsJjarZBpkaUKLxDYtfaPyiXicvDXLIREpxxgKh43mKkEptDg59FDGi8RLd6kz6CjuevZjXyx3lcyUhBcx71r5TXn6g5IojJz0V3A%2BgWG9qe74hR2dKOn6on9qkuWw9IpcdO";
        aq.ajax(url, JSONObject.class, this, "jsonCallback");

    }

    public void jsonCallback(String url, JSONObject json, AjaxStatus status) {

        if (json != null) {
            //successful ajax call
            Toast.makeText(AQueryActivity.this, json.toString(), Toast.LENGTH_LONG).show();
        } else {
            //ajax error
        }

    }

    //带进度条的下载
    public void fileDown() {
        String url = "http://download.souyue.mobi/d3api2/download/souyue_v4.0.2_20141103.apk";

        File ext = Environment.getExternalStorageDirectory();
        File target = new File(ext, "aquery/myfolder/souyue_v4.0.2_20141103.apk");

        aq.progress(R.id.progress).download(url, target, new AjaxCallback<File>() {

            public void callback(String url, File file, AjaxStatus status) {

                if (file != null) {
                    toast("File:" + file.length() + ":");
                } else {
                    toast("Failed");
                }
            }

        });
    }

    private void toast(String s) {
        Toast.makeText(AQueryActivity.this, s, Toast.LENGTH_LONG).show();
    }


    public void getHomePageNewsCallback(JSONObject json){

        Log.d("back");
    }
}