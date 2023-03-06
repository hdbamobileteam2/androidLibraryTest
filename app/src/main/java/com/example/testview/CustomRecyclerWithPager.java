package com.example.testview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;

import java.util.List;

public class CustomRecyclerWithPager extends ConstraintLayout {

    /** Core Items*/
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    /** Core Components*/
    RecyclerView recyclerView;
    IndefinitePagerIndicator indefinitePagerIndicator;
    CustomRecyclerViewAdapter customRecyclerViewAdapter;

    int textStyleRes;
    int titleStyleRes;

    public CustomRecyclerWithPager(Context context) {
        super(context);
        this.mContext=context;
        initView();
    }

    public CustomRecyclerWithPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.attrs=attrs;
        initView();
    }

    public CustomRecyclerWithPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        this.attrs=attrs;
        this.styleAttr=defStyleAttr;
        initView();
    }

    private void initView() {
        this.view=this;
        inflate(mContext, R.layout.custom_recycler_with_pager_layout,this);

        TypedArray arr = mContext.obtainStyledAttributes(attrs,R.styleable.CustomRecyclerWithPager,
                styleAttr, R.style.Widget_AppTheme_CustomRecyclerWithPager);
        textStyleRes = arr.getResourceId(R.styleable.CustomRecyclerWithPager_crvpTextAppearance, androidx.appcompat.R.style.TextAppearance_AppCompat);
        titleStyleRes = arr.getResourceId(R.styleable.CustomRecyclerWithPager_crvpTitleAppearance, androidx.appcompat.R.style.TextAppearance_AppCompat);

        recyclerView = findViewById(R.id.recyclerView);
        indefinitePagerIndicator = findViewById(R.id.recyclerview_pager_indicator);
        indefinitePagerIndicator.attachToRecyclerView(recyclerView);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        customRecyclerViewAdapter = new CustomRecyclerViewAdapter(getContext(), textStyleRes, titleStyleRes);
        recyclerView.setAdapter(customRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        arr.recycle();
    }

    public void setUpAdapter(String[] imageContent, String[] imageTitle, List<Drawable> images) {
        customRecyclerViewAdapter = new CustomRecyclerViewAdapter(getContext(), textStyleRes, titleStyleRes, imageContent, imageTitle, images);
        recyclerView.setAdapter(customRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
    }
}
