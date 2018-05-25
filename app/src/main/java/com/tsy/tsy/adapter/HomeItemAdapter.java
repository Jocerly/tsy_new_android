package com.tsy.tsy.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tsy.tsy.R;
import com.tsy.tsy.entry.CategoriesBean;
import com.tsy.tsy.entry.CategoryBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author：wangzihang
 * date： 2017/8/8 19:15
 * desctiption：
 * e-mail：wangzihang@xiaohongchun.com
 */

public class HomeItemAdapter extends BaseAdapter {

    private Context context;
    private List<CategoriesBean> thirdList = new ArrayList<>();

    public HomeItemAdapter(Context context, List<CategoriesBean> thirdList) {
        this.context = context;
        this.thirdList = thirdList;
    }


    @Override
    public int getCount() {
        return thirdList.size();
    }

    @Override
    public Object getItem(int position) {
        return thirdList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CategoriesBean subcategory = thirdList.get(position);
        ViewHold viewHold = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_home_category, null);
            viewHold = new ViewHold();
            viewHold.tv_name = convertView.findViewById(R.id.item_home_name);
            viewHold.iv_icon = convertView.findViewById(R.id.item_album);
            convertView.setTag(viewHold);
        } else {
            viewHold = (ViewHold) convertView.getTag();
        }
        viewHold.tv_name.setText(subcategory.getName());
        Glide.with(context)
                .load(subcategory.getImgURL())
                .into(viewHold.iv_icon);
        return convertView;


    }

    private static class ViewHold {
        private TextView tv_name;
        private ImageView iv_icon;
    }

}
