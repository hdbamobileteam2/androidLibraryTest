package com.example.testview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.core.widget.TextViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TestView extends ConstraintLayout {

    /** Core Items*/
    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private View view;

    /** Core Components*/
    ImageView image;
    View alphaLayer;
    TextView textView;
    FloatingActionButton floatingActionButton;

    /** Attributes **/
    int imageFileId;
    int imageTintColorResource;
    int imageTintColor;
    Drawable imageFile;
    Drawable gradient;
    String text;
    int textStyleRes;

    public TestView(Context context) {
        super(context);
        this.mContext=context;
        initView();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        this.attrs=attrs;
        initView();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext=context;
        this.attrs=attrs;
        this.styleAttr=defStyleAttr;
        initView();
    }

    private void initView() {
        this.view=this;
        inflate(mContext, R.layout.testview_layout,this);

        TypedArray arr = mContext.obtainStyledAttributes(attrs,R.styleable.TestView,
                styleAttr,R.style.Widget_AppTheme_TestView);

        textView = findViewById(R.id.textView);
        image = findViewById(R.id.wall);
        alphaLayer = findViewById(R.id.view);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        imageFileId = arr.getResourceId(R.styleable.TestView_image, -1);
        if(imageFileId!=-1) {
            imageFile = AppCompatResources.getDrawable(mContext, imageFileId);
            image.setImageDrawable(imageFile);
        }

        imageTintColorResource = arr.getColor(R.styleable.TestView_imageTintColor, getResources().getColor(R.color.white));
        imageTintColor= ContextCompat.getColor(mContext, imageTintColorResource);
        ImageViewCompat.setImageTintList(image, ColorStateList.valueOf(imageTintColor));

        gradient=arr.getDrawable(R.styleable.TestView_gradient);
        if(gradient!=null){
            alphaLayer.setBackground(gradient);
        }

        text=arr.getString(R.styleable.TestView_text);
        if(text!=null){
            textView.setText(text);
        }

        textStyleRes = arr.getResourceId(R.styleable.TestView_textAppearance, androidx.appcompat.R.style.TextAppearance_AppCompat);
        TextViewCompat.setTextAppearance(textView, textStyleRes);

        floatingActionButton.setOnClickListener(view -> {
            setText("Ouch, don't cut me!");
            floatingActionButton.setClickable(false);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setText(getResources().getString(R.string.app_name));
                    floatingActionButton.setClickable(true);
                }
            }, 5000);
        });

        arr.recycle();
    }

    public FloatingActionButton getFloatingActionButton(){
        return floatingActionButton;
    }

    public void setText(String text){
        textView.setText(text);
    }

    @Nullable
    @Override
    public View getChildAt(int position) {
        return null;
    }

//    public void setScaleType(ImageView.ScaleType scaleType){
//        image.setScaleType(scaleType);
//    }
//
//    public void setGradient(Drawable gradient){
//        alphaLayer.setBackground(gradient);
//    }
//
//    public void setDrawableImage(Drawable imageFile){
//        image.setImageDrawable(imageFile);
//    }
//
//    public void setDrawableImage(int imageFile, int imageError, int imagePlaceHolder,
//                                 ImageView.ScaleType scaleType) {
//
//        image.setScaleType(scaleType);
//        Glide
//                .with(mContext)
//                .load(imageFile)
//                .placeholder(imagePlaceHolder)
//                .crossFade()
//                .error(imageError)
//                .into(image);
//
//    }
//
//    public void setUrlImage(String url, int imageError, int imagePlaceHolder,
//                            ImageView.ScaleType scaleType) {
//        image.setScaleType(scaleType);
//        Glide
//                .with(mContext)
//                .load(url)
//                .placeholder(imagePlaceHolder)
//                .crossFade()
//                .dontAnimate()
//                .error(imageError)
//                .into(image);
//
//    }
//
//    public void setResImage(int resID,ImageView.ScaleType scaleType) {
//        image.setScaleType(scaleType);
//        image.setImageResource(resID);
//    }
}
