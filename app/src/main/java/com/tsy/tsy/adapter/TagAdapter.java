package com.tsy.tsy.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {
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
    public int getItemCount() {
        return tags.size() >= 12 ? 4 : tags.size() / 3 + ((tags.size() % 3) > 0 ? 1 : 0);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(context).inflate(R.layout.item_tags, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        setTagToLayout(viewHolder.tag_layout, position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void setTagToLayout(final LinearLayout tag_layout, int position) {
        JCLoger.debug(tag_layout.getChildCount() + "-----");
        for (int i = 0; i < 3; i++) {
            final int p = position * 3 + i;
            if (p == tags.size()) {
                return;
            }
            View view = LayoutInflater.from(context).inflate(R.layout.item_tag, tag_layout, false);
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

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout tag_layout;

        public ViewHolder(View itemView) {
            super(itemView);
            tag_layout = itemView.findViewById(R.id.tag_layout);
        }
    }

    public interface OnTagClickListener {
        void tagClick(int positon, List<String> choices);
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.onTagClickListener = onTagClickListener;
    }
}
