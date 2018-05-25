package com.tsy.tsy.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.tsy.tsy.R;
import com.tsy.tsy.entry.CategoriesBean;
import com.tsy.tsy.entry.CategoryBean;
import com.tsy.tsy.entry.SecondCategoriesBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.tsy.base.views.listView.CustomerGridView;

/**
 * 右侧主界面ListView的适配器
 *
 * @author Administrator
 */
public class HomeAdapter extends BaseAdapter {
    private Context context;
    private List<SecondCategoriesBean> secondList = new ArrayList<>();
    private OnItemCklickListener onItemCklickListener;

    public HomeAdapter(Context context, List<SecondCategoriesBean> secondList) {
        this.context = context;
        this.secondList = secondList;
    }

    @Override
    public int getCount() {
        return secondList.size();
    }

    @Override
    public Object getItem(int position) {
        return secondList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home, null);
            viewHold = new ViewHold();
            viewHold.gridView = convertView.findViewById(R.id.gridView);
            viewHold.blank = convertView.findViewById(R.id.tv_titile);
            viewHold.txtAll = convertView.findViewById(R.id.txtAll);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        final SecondCategoriesBean secondCategoriesBean = secondList.get(position);
        viewHold.blank.setText(secondCategoriesBean.getSubCategories().getName());
        HomeItemAdapter adapter = new HomeItemAdapter(context, secondCategoriesBean.getSubCategories().getSubCategories());
        adapter.setOnItemCklickListener(new HomeItemAdapter.OnItemCklickListener() {
            @Override
            public void onItemCkick(CategoriesBean subcategory) {
                onItemCklickListener.onChileItemCkick(subcategory);
            }
        });
        viewHold.gridView.setAdapter(adapter);
        viewHold.txtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemCklickListener.onParentItemCkick(position, secondCategoriesBean.getSubCategories().getId());
            }
        });
        return convertView;
    }

    private static class ViewHold {
        private CustomerGridView gridView;
        private TextView blank;
        private TextView txtAll;
    }

    public interface OnItemCklickListener {
        void onParentItemCkick(int position, String id);

        void onChileItemCkick(CategoriesBean subcategory);
    }

    public void setOnItemCklickListener(OnItemCklickListener onItemCklickListener) {
        this.onItemCklickListener = onItemCklickListener;
    }
}
