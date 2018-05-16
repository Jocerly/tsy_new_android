package com.huanju.chajiandemo;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by DELL-PC on 2017/2/22.
 */

public class TestActivityTwo extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test);
//        ScrollView ss = new ScrollView(this);
//        LinearLayout layout = new LinearLayout(this);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.drawable.meinv);
//
//
//        ImageView imageView2 = new ImageView(this);
//        imageView2.setImageResource(R.mipmap.ic_launcher);
//
//
//        TextView textView = new TextView(this);
//        textView.setText(getResources().getText(R.string.app_name));
//        textView.setTextColor(Color.RED);
//        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,20);
//
//
//
//
//        layout.addView(imageView);
//        layout.addView(imageView2);
//        layout.addView(textView);
//        ss.addView(layout);
//        setContentView(ss);
    }
}
