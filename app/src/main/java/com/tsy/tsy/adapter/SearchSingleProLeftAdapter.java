package com.tsy.tsy.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tsy.tsy.R;
import com.tsy.tsy.entry.CategoriesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 左侧菜单ListView的适配器
 *
 * @author Administrator
 */
public class SearchSingleProLeftAdapter extends BaseAdapter {
    private Context context;
    private String id = "";
    private List<CategoriesBean> list = new ArrayList<>();

    public SearchSingleProLeftAdapter(Context context, List<CategoriesBean> list) {
        this.context = context;
        this.list = list;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_search_single_left, null);
            holder.tv_name = convertView.findViewById(R.id.item_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (list.get(position).getId() == id) {
            holder.tv_name.setBackgroundColor(Color.WHITE);
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.blue));
        } else {
            holder.tv_name.setBackgroundColor(context.getResources().getColor(R.color.light_grey));
            holder.tv_name.setTextColor(context.getResources().getColor(R.color.black));
        }
        holder.tv_name.setText(list.get(position).getName());
        return convertView;
    }

    public void setData(List<CategoriesBean> list) {
        this.list = list;
    }

    static class ViewHolder {
        private TextView tv_name;
    }
}
