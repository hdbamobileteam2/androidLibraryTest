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

import java.util.List;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.MyViewHolder> implements View.OnTouchListener {

    private final LayoutInflater inflater;
    private String[] imageContent={};
    private String[] imageTitle={};
    private List<Drawable> images;
    private int textStyleRes;
    private int titleStyleRes;

    Context ctx;

    public CustomRecyclerViewAdapter(Context ctx, int textStyleRes, int titleStyleRes){
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        this.images = null;
        this.textStyleRes = textStyleRes;
        this.titleStyleRes = titleStyleRes;
    }

    public CustomRecyclerViewAdapter(Context ctx, int textStyleRes, int titleStyleRes, String[] imageContent, String[] imageTitle, List<Drawable> images){
        this.ctx = ctx;
        inflater = LayoutInflater.from(ctx);
        this.imageTitle = imageTitle;
        this.imageContent = imageContent;
        this.images = images;
        this.textStyleRes = textStyleRes;
        this.titleStyleRes = titleStyleRes;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_adapter, parent, false);
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
        }
        holder.content.setText(description);
        holder.tutorialTitle.setText(title);
        holder.content.setTextAppearance(textStyleRes);
        holder.tutorialTitle.setTextAppearance(titleStyleRes);
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
            return 0;
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