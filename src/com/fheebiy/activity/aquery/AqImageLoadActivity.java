package com.fheebiy.activity.aquery;

import android.app.Activity;
import android.os.Bundle;
import com.androidquery.AQuery;
import com.fheebiy.R;

/**
 * Created by bob zhou on 14-12-26.
 *
 * AQuery load image
 */
public class AqImageLoadActivity extends Activity {
    private AQuery aq;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aqimgs);

        aq = new AQuery(this);

        aq.id(R.id.img1).image("http://souyue-image.b0.upaiyun.com/user/0004/15063854.jpg");
        aq.id(R.id.img2).image("http://souyue-image.b0.upaiyun.com/user/0004/15069728.jpg");
        aq.id(R.id.img3).image("http://souyue-image.b0.upaiyun.com/user/0004/15066483.jpg");
        aq.id(R.id.img4).image("http://souyue-image.b0.upaiyun.com/user/0004/15065031.jpg");
        aq.id(R.id.img5).image("http://souyue-image.b0.upaiyun.com/user/0004/15067594.jpg");
        aq.id(R.id.img6).image("http://souyue-image.b0.upaiyun.com/user/0004/15068947.jpg");
    }
}