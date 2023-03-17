package com.example.testview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.shape.RelativeCornerSize;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;

public class CustomButton extends MaterialButton {

    /** Core Items*/
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    int textStyleRes, textStyleResAlt;
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
        textStyleResAlt = arr.getResourceId(R.styleable.CustomButton_customButtonTextAppearanceUnchecked, androidx.appcompat.R.style.TextAppearance_AppCompat);
        colorRes = arr.getResourceId(R.styleable.CustomButton_customButtonBackgroundColor, R.color.white);

        this.setText(text);
        this.setTextAppearance(textStyleRes);
        this.setBackgroundColor(ResourcesCompat.getColor(getResources(), colorRes, null));

        this.setStrokeWidth(10);
        this.setStrokeColor(ColorStateList.valueOf(ResourcesCompat.getColor(getResources(), colorRes, null)));

        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel()
                .toBuilder()
                .setAllCorners(new RoundedCornerTreatment())
                .setAllCornerSizes(new RelativeCornerSize(0.5f))
                .build();
        this.setShapeAppearanceModel(shapeAppearanceModel);

        this.setPadding(70,70,70,70);

        arr.recycle();
    }

    public void toggleCheck(boolean isChecked) {
        if (isChecked) {
            this.setBackgroundColor(ResourcesCompat.getColor(getResources(), colorRes, null));
            this.setTextAppearance(textStyleRes);
        } else {
            this.setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
            this.setTextAppearance(textStyleResAlt);
        }
    }
}
