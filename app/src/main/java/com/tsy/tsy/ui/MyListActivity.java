package com.tsy.tsy.ui;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.tsy.tsy.BaseActivity;
import com.tsy.tsy.R;
import com.tsy.tsy.adapter.HomeAdapter;
import com.tsy.tsy.adapter.MenuAdapter;
import com.tsy.tsy.entry.CategoriesBean;
import com.tsy.tsy.entry.CategorysBean;
import com.tsy.tsy.entry.SecondCategoriesBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 三级列表展示三级分类信息
 * Created by Jocerly on 2018/4/17.
 */

public class MyListActivity extends BaseActivity {
    @BindView(R.id.lv_menu)
    ListView lvLeft;
    @BindView(R.id.lv_home)
    ListView lvRight;
    @BindView(R.id.tv_titile)
    TextView tvTitile;
    @BindView(R.id.txtAll)
    TextView txtAll;

    private List<CategoriesBean> firstList = new ArrayList<>();//一级分类列表数据
    private List<SecondCategoriesBean> secondList = new ArrayList<>();//二级分类列表数据，包含一级分类的id

    private MenuAdapter menuAdapter;
    private HomeAdapter homeAdapter;
    private int visibleItem = 0;
    private String secondId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    public void initView() {
        menuAdapter = new MenuAdapter(this, firstList);
        lvLeft.setAdapter(menuAdapter);
        lvLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String cId = firstList.get(position).getId();
                for (int i = 0; i < secondList.size(); i++) {
                    if (cId.equals(secondList.get(i).getId())) {
                        lvRight.setSelection(i);
                        break;
                    }
                }
            }
        });

        homeAdapter = new HomeAdapter(this, secondList);
        homeAdapter.setOnItemCklickListener(onItemCklickListener);
        lvRight.setAdapter(homeAdapter);
        lvRight.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (visibleItem != firstVisibleItem) {
                    visibleItem = firstVisibleItem;
                    menuAdapter.setId(secondList.get(firstVisibleItem).getId());
                    showTopTxt();
                }
            }
        });
    }

    @Override
    public void initData() {
        String json = getJson(this, "category.json");
        CategorysBean categorysBean = JSONObject.parseObject(json, CategorysBean.class);
        firstList = categorysBean.getCategories();

        String id = "";
        for (int i = 0; i < firstList.size(); i++) {
            id = firstList.get(i).getId();
            SecondCategoriesBean secondCategoriesBean = null;
            for (CategoriesBean categoriesBean : firstList.get(i).getSubCategories()) {
                secondCategoriesBean = new SecondCategoriesBean();
                secondCategoriesBean.setId(id);
                secondCategoriesBean.setSubCategories(categoriesBean);
                secondList.add(secondCategoriesBean);
            }
        }
        homeAdapter.notifyDataSetChanged();
        menuAdapter.setData(firstList);
        visibleItem = 0;
        menuAdapter.setId(firstList.get(0).getId());
        showTopTxt();
    }

    private void showTopTxt() {
        secondId = secondList.get(visibleItem).getSubCategories().getId();
        tvTitile.setText(secondList.get(visibleItem).getSubCategories().getName());
        menuAdapter.notifyDataSetChanged();
    }

    HomeAdapter.OnItemCklickListener onItemCklickListener = new HomeAdapter.OnItemCklickListener() {
        @Override
        public void onParentItemCkick(int position, String id) {
            secondId = id;
            toast(secondId);
        }

        @Override
        public void onChileItemCkick(CategoriesBean subcategory) {
            toast(subcategory.getName());
        }
    };

    @OnClick(R.id.txtAll)
    public void clickScanAll() {
        toast(secondId);
    }

    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
