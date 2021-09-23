package com.hfad.newfilms;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hfad.newfilms.db.FilmsItem;

import java.util.ArrayList;
import java.util.List;

public class FilmsItemAdapter extends RecyclerView.Adapter<FilmsItemViewHolder> {


    private List<FilmsItem> filmsItemList = new ArrayList<>();
    private final OnDetailFilmsClickListener listener;

    public interface OnDetailFilmsClickListener{
        void OnDetailItemClick(FilmsItem film);
        void onLikeClick(FilmsItem film);
    }

    public FilmsItemAdapter(OnDetailFilmsClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilmsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_films, parent, false);
        return new FilmsItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsItemViewHolder holder, int position) {

        FilmsItem film = filmsItemList.get(position);
        holder.text_name.setText(film.getName());


        Glide.with(holder.image.getContext())
                .load(film.imageResourseId)
                .placeholder(R.drawable.ic_baseline_auto_awesome_24)
                .into(holder.image);

        if(film.isLiked){
            holder.likeButton.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            holder.likeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null){
                listener.OnDetailItemClick(filmsItemList.get(position));
            }
        });

        holder.likeButton.setOnClickListener(v ->{
            if(listener != null) {
                listener.onLikeClick(filmsItemList.get(position));

            }
        });
    }

    public void updateList(List<FilmsItem> list){
        this.filmsItemList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return filmsItemList.size();
    }
}
