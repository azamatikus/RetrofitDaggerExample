package com.azamat.retrofitexample.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.azamat.retrofitexample.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class Util {

    public  static void loadImage(ImageView view, String url, CircularProgressDrawable progressDrawable){

        RequestOptions options = new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(view);
    }

    public static CircularProgressDrawable getProgressDrawable (Context context){
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(50f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
