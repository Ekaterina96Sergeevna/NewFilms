package com.hfad.newfilms;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FilmsItemAdapter extends RecyclerView.Adapter<FilmsItemViewHolder> {
    private List<FilmsItem> items;
    public OnDetailFilmsClickListener listener;


    public interface OnDetailFilmsClickListener{
        void OnDetailItemClick(int filmId);
        void onLikeClick(int filmId);
    }

    public FilmsItemAdapter(List<FilmsItem> items, OnDetailFilmsClickListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FilmsItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilmsItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_films, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsItemViewHolder holder, int position) {
//        if (holder instanceof FilmsItemViewHolder){
//            ((FilmsItemViewHolder) holder).bind(items.get(position));
//        }

        FilmsItem film = items.get(position);

        Glide.with(holder.image.getContext())
                .load(film.imageResourseId)
//                .centerCrop()
                .placeholder(R.drawable.ic_baseline_local_movies_24)
                .into(holder.image);

        holder.text_name.setText(film.getName());
//        holder.image.setImageResource(film.imageResourseId);
//        holder.text_name.setText(film.name);

        if (film.isLiked()) {
            holder.likeButton.setImageResource(R.drawable.ic_baseline_favorite_24);
        } else {
            holder.likeButton.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }

        holder.itemView.setOnClickListener(v -> {
            if(listener != null)
                listener.OnDetailItemClick(items.get(position).getItemId());
        });

        holder.likeButton.setOnClickListener(v -> {
            if (listener != null)
                listener.onLikeClick(items.get(position).getItemId());
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
