package com.hfad.newfilms;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hfad.newfilms.db.FilmsItem;

public class FilmsItemViewHolder extends RecyclerView.ViewHolder{
    public ImageView image;
    public TextView text_name;
    public ImageView likeButton;

    public FilmsItemViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        text_name = itemView.findViewById(R.id.text_name);
        likeButton = itemView.findViewById(R.id.heart);


//    public void bind (FilmsItem filmsItem){
//        image.setImageResource(filmsItem.imageResourseId);
//        text_name.setText(filmsItem.getName());

        }
}
