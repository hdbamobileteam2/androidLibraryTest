package com.example.testview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TestView extends RelativeLayout {

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
    Drawable imageFile;
    Drawable gradient;
    String text;

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
                styleAttr,0);

        imageFileId=arr.getResourceId(R.styleable.TestView_image, -1);
        gradient=arr.getDrawable(R.styleable.TestView_gradient);
        text=arr.getString(R.styleable.TestView_text);

        textView=(TextView)findViewById(R.id.textView);
        image=(ImageView)findViewById(R.id.wall);
        alphaLayer= findViewById(R.id.view);
        floatingActionButton = findViewById(R.id.floatingActionButton);

        if(imageFileId!=-1) {
            imageFile = AppCompatResources.getDrawable(mContext, imageFileId);
            image.setImageDrawable(imageFile);
        }

        if(gradient!=null){
            alphaLayer.setBackground(gradient);
        }

        if(text!=null){
            textView.setText(text);
        }

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
