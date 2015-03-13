package com.fheebiy.activity.basic;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.fheebiy.R;
import com.fheebiy.http.Http;

/**
 * Created by bob zhou on 15-2-10.
 * AsyncTask，在doInBackground中发送HTTP请求，在onPostExecute中处理返回结果
 * 需要继承AsyncTask，实现里面的abstract方法
 */
public class AsyncTaskActivity extends Activity {

    private Button btn;

    private TextView textView;

    private String url = "http://api2.souyue.mobi/d3api2/interest/prime.head.groovy?vc=4.2&sy_c=1GxCyqbRFhHzlWDtZjTZaQ7tac0cAKDLl32f0oBSiy6tjljd3dRf79eI8anlOHF%2F86IKRP0YSzP9X%2FOG%2BoYJMy4kSNKFGaaoMqsWJJquet9OysqKJYB7uApXC%2FFeaV4BYd%2BWXwzzx2Ba3r3zReDs0knGcR270Fj8UxQB05ganG5IaUu5msFOhOxr%2Fc3Jaika%2ButZVX0tczddVQ3y3Ofcz7qSrkTwhsHi7BsKvuHKViWbEUJ%2FW8ygcOXVuYkMpGVymsOMPUkuDmyM%2Fo%2BG5QDcBhpisL3TVMjMgwTU0JVmHWl6fy0ZrNoXK97lQ2ppMH4aN5c0M7fH43rojm66uUwyyQ8DRXPMVDVo8Q%3D%3D";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task);
        btn = (Button) findViewById(R.id.btn1);
        textView = (TextView)findViewById(R.id.textview);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute(url);
            }
        });
    }


    class MyAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... params) {
            String str = Http.postData(params[0], null);
            return str;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
           // Toast.makeText(AsyncTaskActivity.this, s, Toast.LENGTH_SHORT).show();
            textView.setText(s);
        }
    }


}