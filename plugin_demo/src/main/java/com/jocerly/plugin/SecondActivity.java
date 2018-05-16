package com.jocerly.plugin;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
    ImageView imgView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imgView = findViewById(R.id.imgView);
    }

    public void imgClick(View view){
        Toast.makeText(this, "点击啦第二个", Toast.LENGTH_SHORT).show();
    }
}
