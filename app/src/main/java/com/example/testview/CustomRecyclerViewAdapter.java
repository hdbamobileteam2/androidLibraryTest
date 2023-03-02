package com.example.testview;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder> implements View.OnTouchListener {

    private final LayoutInflater inflater;
    private int[] myImageList = new int[]{R.drawable.easeintro1enzh_svg, R.drawable.easeintro2_svg};
    private String[] imageContent={};
    private String[] imageTitle={};
    private List<Drawable> images;

    Context ctx;

    public CustomRecyclerViewAdapter(Context ctx){
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        this.images = null;
        imageTitle = new String[] {ctx.getResources().getString(R.string.ar_ease_intro_slider1_title), ctx.getResources().getString(R.string.ar_ease_intro_slider2_title)};
        imageContent = new String[] {ctx.getResources().getString(R.string.ar_ease_intro_slider1_caption), ctx.getResources().getString(R.string.ar_ease_intro_slider2_caption)};
        //  this.imageModelArrayList = imageModelArrayList;
    }

    public CustomRecyclerViewAdapter(Context ctx, String[] imageContent, String[] imageTitle, List<Drawable> images){
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        this.imageTitle = imageTitle;
        this.imageContent = imageContent;
        this.images = images;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.ar_landing_page_adapter_new, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Display display = ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point size = new Point();

        String title, description;
        title = imageTitle[position];
        description = imageContent[position];

//        if(imageModelArrayList != null){
//            title = imageModelArrayList.get(position).getLayoutName();
//            description = imageModelArrayList.get(position).getLayoutDescription();
//        }else{
//            title = imageTitle[position];
//            description = imageContent[position];
//        }

        try {
            display.getRealSize(size);
        } catch (NoSuchMethodError err) {
            display.getSize(size);
        }
        int width = size.x;
        int height = size.y;


        if(images != null){
            holder.imageView.setImageDrawable(images.get(position));
        }else{
            holder.imageView.setImageResource(myImageList[position]);
        }
        holder.content.setText(description);
        holder.tutorialTitle.setText(title);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        v.getParent().requestDisallowInterceptTouchEvent(true);
        return false;
    }

    @Override
    public int getItemCount() {
        if(images != null){
            return images.size();
        }else{
            return myImageList.length;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView content,tutorialTitle;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.arImageView);
            content=itemView.findViewById(R.id.arContent);
            tutorialTitle=itemView.findViewById(R.id.arTutorialTitle);

        }

    }
}