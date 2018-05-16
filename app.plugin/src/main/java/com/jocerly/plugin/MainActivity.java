package com.jocerly.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    ImageView imgView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgView = findViewById(R.id.imgView);
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgClick();
            }
        });
    }

    public void imgClick(){
        Toast.makeText(that, "点击啦", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(that, SecondActivity.class);
        startActivity(intent);
    }
}
