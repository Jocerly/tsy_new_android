package com.tsy.tsy.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.didi.virtualapk.PluginManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tsy.tsy.BaseFragment;
import com.tsy.tsy.R;
import com.tsy.tsy.dialog.SortDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.tsy.base.uitls.JCLoger;

/**
 * Created by Jocerly on 2018/4/16.
 */
public class BuyFragment extends BaseFragment {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.img_filter_category)
    ImageView imgFilterCategory;
    @BindView(R.id.tv_filter_category)
    TextView tvFilterCategory;
    @BindView(R.id.sp_category_filter)
    LinearLayout spCategoryFilter;
    @BindView(R.id.tv_sort_category)
    TextView tvSortCategory;
    @BindView(R.id.sp_category_sort)
    LinearLayout spCategorySort;
    @BindView(R.id.btnAdd)
    Button btnAdd;
    @BindView(R.id.btnInto)
    Button btnInto;
    @BindView(R.id.rl_filter_sort)
    RelativeLayout rlFilterSort;
    @BindView(R.id.btnDidi)
    Button btnDidi;

    private SortDialog sortDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buy, null);
        aty = getActivity();
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        initData(view);
        return view;
    }

    @Override
    public void initView(View view) {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(1000, true);
                text.setText("刷新成功");
            }
        });

        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1000, true, true);
                text.setText("加载成功");
            }
        });
    }

    @Override
    public void initData(View view) {
    }

    @OnClick(R.id.sp_category_sort)
    public void choiceSort() {
        if (sortDialog == null) {
            sortDialog = new SortDialog(getActivity());
            sortDialog.setOnSeclectListener(onSeclectListener);
        }
        sortDialog.show();
    }

    SortDialog.OnSeclectListener onSeclectListener = new SortDialog.OnSeclectListener() {
        @Override
        public void seclectItem(String sort) {
            tvSortCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            if ("价格升序".equals(sort)) {
                sort = "价格";
                tvSortCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_money_a, 0);
            } else if ("价格降序".equals(sort)) {
                sort = "价格";
                tvSortCategory.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_money_b, 0);
            }
            tvSortCategory.setText(sort);
        }
    };

    /**
     * 事先放置到SD卡根目录的plugin.apk
     * 现实场景中是有服务端下发
     */
    @OnClick(R.id.btnAdd)
    public void addClick() {
    }

    /**
     * 点击跳往插件app的activity，一律跳转到PRoxyActivity
     */
    @OnClick(R.id.btnInto)
    public void intoClick() {
    }

    @OnClick(R.id.btnDidi)
    public void intoDidiPlugin() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "plugin.apk");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
