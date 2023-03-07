package com.example.testview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.button.MaterialButton;

public class CustomButton extends MaterialButton {

    /** Core Items*/
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    int textStyleRes;
    int colorRes;
    String text;

    public CustomButton(@NonNull Context context) {
        super(context);
        this.mContext=context;
        initView();
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.attrs=attrs;
        initView();
    }

    public CustomButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        this.attrs=attrs;
        this.styleAttr=defStyleAttr;
        initView();
    }

    private void initView() {
        this.view=this;
        TypedArray arr = mContext.obtainStyledAttributes(attrs,R.styleable.CustomButton,
                styleAttr, R.style.Widget_AppTheme_CustomButton);
        text = arr.getString(R.styleable.CustomButton_customButtonText);
        textStyleRes = arr.getResourceId(R.styleable.CustomButton_customButtonTextAppearance, androidx.appcompat.R.style.TextAppearance_AppCompat);
        colorRes = arr.getResourceId(R.styleable.CustomButton_customButtonBackgroundColor, R.color.white);

        this.setText(text);
        this.setTextAppearance(textStyleRes);
        this.setBackgroundColor(ResourcesCompat.getColor(getResources(), colorRes, null));

        arr.recycle();
    }
}
