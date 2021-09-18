package com.hfad.newfilms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment implements FilmsItemAdapter.OnDetailFilmsClickListener{
    final List<FilmsItem> items = new ArrayList<>();

    public static final String TAG = "MainFragment";
    private RecyclerView recyclerView;
    AddFilmFragment addFilmFragment = new AddFilmFragment();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().filmsService.getFilm().enqueue(new Callback<List<FilmsJson>>() {
            @Override
            public void onResponse(Call<List<FilmsJson>> call, Response<List<FilmsJson>> response) {
              if (response.isSuccessful()){
                  List<FilmsJson> filmsJsonList = response.body();
                  items.clear();
                  for(FilmsJson filmsJson : filmsJsonList){
                      items.add(new FilmsItem(filmsJson));
                  }

                  recyclerView.getAdapter().notifyDataSetChanged();
              } else {
                  Toast.makeText(MainFragment.this.getContext(),
                          "FAIL " + response.code(),
                          Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<FilmsJson>> call, Throwable t) {

                Toast.makeText(MainFragment.this.getContext(),
                        "FAILURE " + t.getClass().getSimpleName(),
                        Toast.LENGTH_SHORT).show();
                if (t instanceof Exception){}
                t.printStackTrace();
            }
        });

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

        //получаем инфо из AddFilmFragment
        getParentFragmentManager().setFragmentResultListener("key1", this, new FragmentResultListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                String name = bundle.getString("name");
                String description = bundle.getString("description");

                //new next Id for new added film
                int size = FilmsItemRepository.getInstance().getItems().size();
                //parse drawable to the String
                FilmsItemRepository.getInstance().getItems().add(new FilmsItem(name, description,
                        R.drawable.ic_baseline_local_movies_24,
                        size,false));

                // new FilmsItem(name, description, "drawable://" + R.drawable.ic_baseline_local_movies_24, false)
                // R.drawable.ic_baseline_local_movies_24

                recyclerView.getAdapter().notifyDataSetChanged();
            }
        });

    }
}