package com.hfad.newfilms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class FilmsDetailedFragment extends Fragment {
    private static final String EXTRA_INT = "EXTRA_INT";
    public static final String TAG = "FilmsDetailedFragment";

    private int filmId = 0;

    public static FilmsDetailedFragment newInstance(int filmId){

        FilmsDetailedFragment fragment = new FilmsDetailedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_INT, filmId);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       return inflater.inflate(R.layout.fragment_detailed_films, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null){
            filmId = getArguments().getInt(EXTRA_INT, 0);
        }
        FilmsItem choiceFilm = null;
        for (FilmsItem filmItem : FilmsItemRepository.getInstance().getItems()) {
            if (filmItem.itemId == filmId) {
                choiceFilm = filmItem;
            }
        }

        ((TextView)view.findViewById(R.id.name_films)).setText(choiceFilm.getName());

        //((ImageView)view.findViewById(R.id.image_films)).setImageResource(choiceFilm.getImageResourseId());
        ImageView photo = (ImageView) view.findViewById(R.id.image_films);
//        photo.setImageResource(choiceFilm.getImageResourceId());
        //use Glide
        Glide.with(getContext())
                .load(choiceFilm.imageUrl)
                .placeholder(R.drawable.ic_baseline_auto_awesome_24)
                .into(photo);

        photo.setContentDescription(choiceFilm.getName());

        ((TextView)view.findViewById(R.id.description)).setText(choiceFilm.getDescription());






    }
}

