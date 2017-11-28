package com.tsy.tsy.ui.base;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tsy.tsy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jay on 17/8/15.
 */

public class ToolbarActivity extends BaseActivity {

    class ToolbarViewHolder {
        @BindView(R.id.toolbar)
        Toolbar toolbar;
//        @BindView(R.id.text_title)
//        TextView title;
    }

    ToolbarViewHolder holder = new ToolbarViewHolder();
    Toolbar toolbar;

    TextView title;

    public boolean canBack() {
        return true;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(holder, this);
        toolbar = holder.toolbar;
//        title = holder.title;

        if (toolbar != null) {
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

//            title.setText(getTitle());

            ActionBar actionBar = getSupportActionBar();
            if (actionBar  != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                if (canBack()) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }
        }
    }

    public void setDisplayShowTitleEnabled(boolean enabled) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(enabled);
        }
    }
}
