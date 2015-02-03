package com.fheebiy.activity.async;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.androidquery.AQuery;
import com.fheebiy.R;
import com.fheebiy.http.async.HttpHelper;
import com.fheebiy.http.async.MJsonHttpResponseHandler;
import com.fheebiy.http.async.ModelHttpResponseHandler;
import com.fheebiy.model.CircleIndexMenuInfo;
import com.fheebiy.model.NewsDetail;
import com.fheebiy.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Lenovo on 15-2-2.
 */
public class AsyncActivity extends Activity {

    private static final String TAG = "AsyncActivity";
    private AQuery aq;


    //错误的 homepage
    //public static final String sy_c = "1a%2F2vnuqq4eCoxxsby28NvDQmSXi9EDmWSvnuMJtVw6bQft0SzbR3prnN3vQqi7iVRFu5issmk%2FufivV3%2FD%2Bf%2BSTVOIYrKyaVPkAer%2BAlPwuikKHWOrrTXoUeTK4gX1iysoMDmyxsPSipXeRUY59oJhXxhNrvb1pcsrdSUfOQ5xI0uOzeB7%2FCiwYWwowROEoJlWlKe3PIo6Nfq3%2F9TByBdVqDeiCcASfzVbuhXGt2KU%2BrdZqIofMcreSN7oqNnqchfxL81Rv6sW%2BIkVb3CEFdHl7jVcJG8WF%2BQoNxtMQYOu3zSmtpamvRzJFs6sKRAMeUNWnYIkFJOhkwyeH17PcrSVK2B%2Fv21bk1IDPCLH03t";

    //正确的 homepage
    public static final String sy_c = "1a%2F2vnuqq4eCoxxsby28NvDQmSXi9EDmWSvnuMJtVw6bQft0SzbR3prnN3vQqi7iVRFu5issmk%2FufivV3%2FD%2Bf%2BSTVOIYrKyaVPkAer%2BAlPwuikKHWOrrTXoUeTK4gX1iysoMDmyxsPSipXeRUY59oJhXxhNrvb1pcsrdSUfOQ5xI0uOzeB7%2FCiwYWwowROEoJlWlKe3PIo6Nfq3%2F9TByBdVqDeiCcASfzVbuhXGt2KU%2BrdZqIofMcreSN7oqNnqchfxL81Rv6sW%2BIkVb3CEFdHl7jVcJG8WF%2BQoNxtMQYOu3zSmtpamvRzJFs6sKRAMeUNWnYIkFJOhkwyeH17PcrSVK2B%2Fv21bk1IDPCLH03t2i";


    //错误的 circleMenu
    //public static final String sy_c_circle = "1Pwr2sMyRMq0uiXlKTqpBZeMRPBDhKas7D04jSyKy2Z9%2B%2FSQnq9x3Ad9JlDs2khr%2Bm7uATQS8x6oA0DUtym%2Fda3wxF57FIMZxvnHL5rSlYIW1UDVmQdG4vgQBQ4HHSRZYkkgiHnA4gQCgDGy6d0aMtwwKTGH0bP68qhrZUNvzaEYt%2F2nNTaVVWDH4tcQKFlq99axHsnuD1vvQtR2Lye85j4CTUZB1msiK44TR6ocvWxKDhdZYmjYpAnpBRBm8ryJOCG5j08uiY%2FhUyS4MN12h8BlkqM26hpI%2B8vGA070nXQQ2Dc0vWU8C5VUFZBqsIBQSWQr2WQuPXRFRNz8HYsjNfq1EwkB%2By0tgg%3D%3D";


    //正确的 circleMenu
    public static final String sy_c_circle = "1Pwr2sMyRMq0uiXlKTqpBZeMRPBDhKas7D04jSyKy2Z9%2B%2FSQnq9x3Ad9JlDs2khr%2Bm7uATQS8x6oA0DUtym%2Fda3wxF57FIMZxvnHL5rSlYIW1UDVmQdG4vgQBQ4HHSRZYkkgiHnA4gQCgDGy6d0aMtwwKTGH0bP68qhrZUNvzaEYt%2F2nNTaVVWDH4tcQKFlq99axHsnuD1vvQtR2Lye85j4CTUZB1msiK44TR6ocvWxKDhdZYmjYpAnpBRBm8ryJOCG5j08uiY%2FhUyS4MN12h8BlkqM26hpI%2B8vGA070nXQQ2Dc0vWU8C5VUFZBqsIBQSWQr2WQuPXRFRNz8HYsjNfq1EwkB%2By0tgg%3D%3D";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async);
        aq = new AQuery(this);

        bindListener();
    }

    private void bindListener() {
        aq.id(R.id.btn1).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HttpHelper.getNews(sy_c, new ModelHttpResponseHandler<NewsDetail>(NewsDetail.class) {
                    @Override
                    public void onSuccess(int statusCode, List<NewsDetail> response) {
                        if (CollectionUtils.isNotEmpty(response)) {
                            for (NewsDetail detail : response) {
                                Log.d(TAG, "ModelHttpResponseHandler title:---->" + detail.getTitle());
                            }
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, NewsDetail response) {

                    }

                    @Override
                    public void onFailure(int statusCode, Throwable e, String msg) {
                        // Log.d(TAG, "onFailure" + statusCode + msg);
                        // e.printStackTrace();
                    }
                });


            }
        });


        aq.id(R.id.btn2).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpHelper.getNews(sy_c, new MJsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try {
                            String jsonStr = response.getJSONArray("body").toString();
                            List<NewsDetail> list = new Gson().fromJson(jsonStr, new TypeToken<List<NewsDetail>>() {
                            }.getType());
                            if (CollectionUtils.isNotEmpty(list)) {
                                for (NewsDetail detail : list) {
                                    Log.d(TAG, "MJsonHttpResponseHandler title:---->" + detail.getTitle());
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Throwable e, String msg) {

                    }
                });
            }
        });


        aq.id(R.id.btn3).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpHelper.getCircleMenu(sy_c_circle, new MJsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, JSONObject response) {
                        try {
                            String jsonStr = response.getJSONObject("body").toString();
                            CircleIndexMenuInfo info = new Gson().fromJson(jsonStr, new TypeToken<CircleIndexMenuInfo>() {
                            }.getType());
                            if (info != null) {
                                Log.d(TAG, "MJsonHttpResponseHandler name--->" + info.getInterestName());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Throwable e, String msg) {

                    }
                });


            }
        });

        aq.id(R.id.btn4).clicked(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpHelper.getCircleMenu(sy_c_circle, new ModelHttpResponseHandler<CircleIndexMenuInfo>(CircleIndexMenuInfo.class) {
                    @Override
                    public void onSuccess(int statusCode, CircleIndexMenuInfo response) {
                        if(response != null){
                            Log.d(TAG, "ModelHttpResponseHandler name--->" + response.getInterestName());
                        }
                    }

                    @Override
                    public void onSuccess(int statusCode, List<CircleIndexMenuInfo> response) {

                    }

                    @Override
                    public void onFailure(int statusCode, Throwable e, String msg) {

                    }
                });
            }
        });


    }
}