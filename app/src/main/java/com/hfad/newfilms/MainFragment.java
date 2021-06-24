package com.hfad.newfilms;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements FilmsItemAdapter.OnDetailFilmsClickListener{
    private List<FilmsItem> items = new ArrayList<>();
    public static final String TAG = "MainFragment";

    private RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void OnDetailItemClick(int filmId) {
        //open the fragment with method newInstance to pass the position
        Fragment fragment = FilmsDetailedFragment.newInstance(filmId);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void onLikeClick(int choiceFilmId) {
        FilmsItemRepository.getInstance().getFavoriteFilms();
        //find the right film by filmId
        FilmsItem choiceFilm = null;
        for (FilmsItem filmItem : FilmsItemRepository.getInstance().getItems()) {
            if (filmItem.itemId == choiceFilmId) {
                choiceFilm = filmItem;
            }
        }

        choiceFilm.isLiked = !choiceFilm.isLiked;
        recyclerView.getAdapter().notifyDataSetChanged(); //обновление
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  items.addAll(FilmsItemRepository.getInstance().getItems());

        recyclerView = view.findViewById(R.id.recyclerView);

        FilmsItemAdapter adaptor = new FilmsItemAdapter(FilmsItemRepository.getInstance().getItems(), this);
        recyclerView.setAdapter(adaptor);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
}