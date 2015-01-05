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

        aq.id(R.id.img1).image("http://img1.gtimg.com/11/1144/114402/11440280_980x1200_0.jpg");
        aq.id(R.id.img2).image("http://img1.gtimg.com/11/1144/114402/11440277_980x1200_0.jpg");
        aq.id(R.id.img3).image("http://img1.gtimg.com/11/1144/114402/11440284_980x1200_0.jpg");
        aq.id(R.id.img4).image("http://img1.gtimg.com/11/1144/114419/11441977_980x1200_0.jpg");
        aq.id(R.id.img5).image("http://img1.gtimg.com/11/1144/114419/11441978_980x1200_0.jpg");
        aq.id(R.id.img6).image("http://img1.gtimg.com/11/1144/114419/11441979_980x1200_0.jpg");
    }
}