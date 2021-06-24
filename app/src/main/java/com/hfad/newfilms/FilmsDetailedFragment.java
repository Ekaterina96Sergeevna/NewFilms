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

public class FilmsDetailedFragment extends Fragment {
    private static final String EXTRA_INT = "EXTRA_INT";
    public static final String TAG = "FilmsDetailedFragment";

    private int position = 0;

    public static FilmsDetailedFragment newInstance(int position){

        FilmsDetailedFragment fragment = new FilmsDetailedFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_INT, position);
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
            position = getArguments().getInt(EXTRA_INT, 0);
        }
        FilmsItem choiceFilm = null;
        for (FilmsItem filmItem : FilmsItemRepository.getInstance().getItems()) {
            if (filmItem.itemId == position) {
                choiceFilm = filmItem;
            }
        }
        ((TextView)view.findViewById(R.id.name_films)).setText(FilmsItem.films[position].name);
        ((ImageView)view.findViewById(R.id.image_films)).setImageResource(FilmsItem.films[position].imageResourseId);
        ((TextView)view.findViewById(R.id.description)).setText(FilmsItem.films[position].description);


    }
}

