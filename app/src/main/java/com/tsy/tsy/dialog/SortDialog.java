package com.tsy.tsy.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tsy.tsy.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.tsy.base.uitls.JCLoger;


public class SortDialog extends Dialog {
    @BindView(R.id.rbSort1)
    RadioButton rbSort1;
    @BindView(R.id.rbSort2)
    RadioButton rbSort2;
    @BindView(R.id.rbSort3)
    RadioButton rbSort3;
    @BindView(R.id.rbSort4)
    RadioButton rbSort4;
    @BindView(R.id.rbSort5)
    RadioButton rbSort5;
    @BindView(R.id.rgSort)
    RadioGroup rgSort;
    private Context context;
    private OnSeclectListener onSeclectListener;

    public SortDialog(Context context) {
        super(context, R.style.MyDialog); //dialog的样式
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.dialog_sort, null);

        Window window = getWindow();
        window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画效果
        window.setContentView(view);
        ButterKnife.bind(this, view);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        window.setAttributes(lp);
        setCanceledOnTouchOutside(true);// 点击Dialog外部消失

        rgSort.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            String sort = ((RadioButton) findViewById(checkedId)).getText().toString();
            JCLoger.debug(checkedId + "-----" + sort);
            if (onSeclectListener != null) {
                onSeclectListener.seclectItem(sort);
            }
            dismiss();
        }
    };


    public interface OnSeclectListener {
        public void seclectItem(String sort);
    }

    public void setOnSeclectListener(OnSeclectListener onSeclectListener) {
        this.onSeclectListener = onSeclectListener;
    }
}
