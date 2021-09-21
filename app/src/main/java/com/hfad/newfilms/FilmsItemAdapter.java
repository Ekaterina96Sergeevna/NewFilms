package com.hfad.newfilms;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.newfilms.service.FilmsItem;

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

        FilmsItem film = items.get(position);
        holder.bind(film);

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

    public int getItemCount() {
        return items.size();
    }
}
