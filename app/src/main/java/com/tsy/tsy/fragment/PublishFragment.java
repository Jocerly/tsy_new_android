package com.tsy.tsy.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import cn.tsy.base.uitls.JCLoger;
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
    @BindView(R.id.tag_layout)
    LinearLayout mtagLayout;

    private List<String> choiceTags = new ArrayList<>();
    int mWidth = 0;

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

    }

    @Override
    public void initData(View view) {
        tags.add("重生成重生成神");
        tags.add("重生成神");
        tags.add("dsds dn");
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
        tagLayout.setTagMaxLength(10);
        tagLayout.setTags(tags);

        setTagToLayout(mtagLayout);
        showChoiceTags();
    }

    private void setTagToLayout(final LinearLayout tag_layout) {
        int lines = tags.size() >= 12 ? 4 : tags.size() / 3 + ((tags.size() % 3) > 0 ? 1 : 0);
        for (int i = 0; i < lines; i++) {
            tag_layout.addView(setTagToLayout(i));
        }
    }


    private LinearLayout setTagToLayout(int line) {
        LinearLayout layout = new LinearLayout(aty);
        for (int i = 0; i < 3; i++) {
            final int p = line * 3 + i;
            if (p < tags.size()) {
                View view = LayoutInflater.from(aty).inflate(R.layout.item_tag, layout, false);
                final TextView mTag = view.findViewById(R.id.txt_tag);
                String tagStr = tags.get(p);
                tagStr = tagStr.length() > 10 ? (tagStr.substring(0, 9) + "...") : tagStr;
                mTag.setText(tagStr);
                mTag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (choiceTags.contains(tags.get(p))) {
                            choiceTags.remove(tags.get(p));
                            mTag.setBackgroundResource(R.drawable.tag_nomal);
                        } else {
                            choiceTags.add(tags.get(p));
                            mTag.setBackgroundResource(R.drawable.tag_press);
                        }
                        showChoiceTags();
                    }
                });
                if (choiceTags.contains(tags.get(p))) {
                    mTag.setBackgroundResource(R.drawable.tag_press);
                } else {
                    mTag.setBackgroundResource(R.drawable.tag_nomal);
                }
                layout.addView(mTag);
            }
        }
        return layout;
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

        choiceTags.clear();
        mtagLayout.removeAllViews();
        setTagToLayout(mtagLayout);
        showChoiceTags();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
