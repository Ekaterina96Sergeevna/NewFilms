package com.hfad.newfilms.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.hfad.newfilms.db.FilmsItem;
import com.hfad.newfilms.FilmsItemRepository;
import com.hfad.newfilms.R;
import com.hfad.newfilms.db.FilmsViewModel;

import java.util.List;

public class FilmsDetailedFragment extends Fragment {
    private static final String EXTRA_INT = "EXTRA_INT";
    public static final String TAG = "FilmsDetailedFragment";

    private int filmId = 0;
    FilmsViewModel filmsViewModel;

    TextView name;
    TextView description;

    public static FilmsDetailedFragment newInstance(){
        return new FilmsDetailedFragment();
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


        filmsViewModel = new ViewModelProvider(requireActivity()).get(FilmsViewModel.class);

        // Update the cached copy of the words in the adapter.
        filmsViewModel.getSelectedFilm().observe(getViewLifecycleOwner(), film -> {

            Log.d("proverka", "hello");
            name = (TextView)view.findViewById(R.id.name_films);
                    name.setText(film.getName());

            ImageView photo = (ImageView) view.findViewById(R.id.image_films);
            Glide.with(getContext())
                    .load(film.imageResourseId)
                    .placeholder(R.drawable.ic_baseline_auto_awesome_24)
                    .into(photo);
            photo.setContentDescription(film.getName());

            description = (TextView)view.findViewById(R.id.description);
                    description.setText(film.getDescription());
        });
    }
}

