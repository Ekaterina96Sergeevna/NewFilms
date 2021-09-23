package com.hfad.newfilms.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hfad.newfilms.db.FilmsItem;
import com.hfad.newfilms.FilmsItemAdapter;
import com.hfad.newfilms.FilmsItemRepository;
import com.hfad.newfilms.R;
import com.hfad.newfilms.db.FilmsViewModel;

public class FavoriteFragment extends Fragment implements FilmsItemAdapter.OnDetailFilmsClickListener {
    public static final String TAG = "FavouriteFragment";
    RecyclerView recyclerView;
    FilmsViewModel filmsViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void OnDetailItemClick(FilmsItem film) {
        Fragment fragment = FilmsDetailedFragment.newInstance();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

        filmsViewModel.select(film);
    }

    public void onLikeClick(FilmsItem film) {
        film.isLiked = !film.isLiked;
        Toast.makeText(getContext(), "Removed from favorite movies", Toast.LENGTH_SHORT).show();
        filmsViewModel.update(film);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewFavorite);
        FilmsItemAdapter adapter = new FilmsItemAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        filmsViewModel = new ViewModelProvider(requireActivity()).get(FilmsViewModel.class);
        filmsViewModel.getAllFilmsIsLike().observe(getViewLifecycleOwner(), filmsItems -> {
            adapter.updateList(filmsItems);
        });



    }
}