package com.tsy.tsy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tsy.tsy.BaseFragment;
import com.tsy.tsy.R;
import com.tsy.tsy.adapter.TagAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.tsy.base.views.CircleProgressBar;
import cn.tsy.base.views.listView.CustomerListView;
import cn.tsy.base.views.tagview.TagContainerLayout;

/**
 * Created by Jocerly on 2018/4/16.
 */

public class PublishFragment extends BaseFragment {
    @BindView(R.id.button3)
    Button button3;
    Unbinder unbinder;
    @BindView(R.id.progress1)
    CircleProgressBar progress1;
    @BindView(R.id.progress2)
    ProgressBar progress2;
    @BindView(R.id.tagLayout)
    TagContainerLayout tagLayout;

    List<String> tags = new ArrayList<>();
    @BindView(R.id.txt_tag_choices)
    TextView txtTagChoices;
    @BindView(R.id.mListView)
    CustomerListView mListView;


    private List<String> choiceTags = new ArrayList<>();
    int mWidth = 0;
    private TagAdapter tagAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_publish, null);
        aty = getActivity();
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData(view);
        return view;
    }

    @Override
    public void initView(View view) {
        DisplayMetrics dm = aty.getResources().getDisplayMetrics();
        mWidth = dm.widthPixels;
        progress1.setOnLoadListener(new CircleProgressBar.OnLoadListener() {
            @Override
            public void sucLoad() {
                progress1.setVisibility(View.GONE);
                progress2.setVisibility(View.VISIBLE);
            }
        });

        tagAdapter = new TagAdapter(aty, tags, mWidth);
        tagAdapter.setOnTagClickListener(new TagAdapter.OnTagClickListener() {
            @Override
            public void tagClick(int positon, List<String> choices) {
                choiceTags = choices;
                showChoiceTags();
            }
        });
        mListView.setAdapter(tagAdapter);
    }

    @Override
    public void initData(View view) {
        tags.add("重生成重生成神");
        tags.add("重生成神");
        tags.add("重生成神生成神dsn ds ds dn");
        tags.add("重生");
        tags.add("成神");
        tags.add("重神");
        tags.add("重生成神成神");
        tags.add("sjdnjs");
        tags.add("skdm");
        tags.add("siodjscnjncjnw");
        tags.add("重生成神重生成神");

        choiceTags.add(tags.get(2));
        choiceTags.add(tags.get(5));
        choiceTags.add(tags.get(6));

//        int w = mWidth / 3 - getResources().getDimensionPixelSize(R.dimen.pad10) * 6;
//        JCLoger.debug(w + "----" + mWidth);
        tagLayout.setTagMaxLength(10);
        tagLayout.setTags(tags);

        tagAdapter.setChoiceTags(choiceTags);
        tagAdapter.notifyDataSetChanged();

        showChoiceTags();

    }

    private void showChoiceTags() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String choiceTag : choiceTags) {
            stringBuilder.append(choiceTag);
            stringBuilder.append(",");
        }
        String tagTmp = stringBuilder.toString();
        if (tagTmp.length() > 1 && tagTmp.endsWith(",")) {
            tagTmp = tagTmp.substring(0, tagTmp.length() - 1);
        }

        txtTagChoices.setText(tagTmp);
    }

    @OnClick(R.id.button3)
    public void onButton() {
        progress2.setVisibility(View.GONE);
        progress1.setVisibility(View.VISIBLE);
        progress1.reStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
