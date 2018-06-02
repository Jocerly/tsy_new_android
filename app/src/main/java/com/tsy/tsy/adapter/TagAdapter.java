package com.tsy.tsy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tsy.tsy.R;

import java.util.ArrayList;
import java.util.List;

import cn.tsy.base.uitls.JCLoger;

public class TagAdapter extends BaseAdapter {
    private Context context;
    private List<String> tags = new ArrayList<>();

    private List<String> choiceTags = new ArrayList<>();

    private int maxW;
    private OnTagClickListener onTagClickListener;

    public TagAdapter(Context context, List<String> tags, int mWidth) {
        this.context = context;
        this.tags = tags;
        maxW = (mWidth - context.getResources().getDimensionPixelOffset(R.dimen.pad10) * 4) / 3;
    }

    @Override
    public int getCount() {
        return tags.size() >= 12 ? 4 : tags.size() / 3 + ((tags.size() % 3) > 0 ? 1 : 0);
    }

    @Override
    public Object getItem(int position) {
        return tags.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tags, parent, false);
            viewHolder.tag_layout = convertView.findViewById(R.id.tag_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        setTagToLayout(viewHolder.tag_layout, position);

        return convertView;
    }

    private void setTagToLayout(final LinearLayout tag_layout, int position) {
        for (int i = 0; i < 3; i++) {
            final int p = position * 3 + i;
            if (p == tags.size()) {
                return;
            }
            View view = LayoutInflater.from(context).inflate(R.layout.item_tag, tag_layout, false);
            final TextView mTag = view.findViewById(R.id.txt_tag);
            mTag.setText(tags.get(p));
            final int finalI = i;
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
                    if (onTagClickListener != null) {
                        onTagClickListener.tagClick(p, choiceTags);
                    }
                }
            });
            if (choiceTags.contains(tags.get(p))) {
                mTag.setBackgroundResource(R.drawable.tag_press);
            } else {
                mTag.setBackgroundResource(R.drawable.tag_nomal);
            }
            tag_layout.addView(mTag);
        }
    }

    public List<String> getChoices() {
        return choiceTags;
    }

    public void setChoiceTags(List<String> choiceTags) {
        this.choiceTags = choiceTags;
    }

    public class ViewHolder {
        LinearLayout tag_layout;

    }

    public interface OnTagClickListener {
        void tagClick(int positon, List<String> choices);
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }
}
