package com.hfad.newfilms.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.hfad.newfilms.R;

public class AddFilmFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_film, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.click_button).setOnClickListener(v -> {
            EditText name = view.findViewById(R.id.title_movie);
            EditText description = view.findViewById(R.id.description_movie);
            Bundle result = new Bundle();
            result.putString("name", name.getText().toString());
            result.putString("description", description.getText().toString());
            getParentFragmentManager().setFragmentResult("key1", result);
           //? getParentFragmentManager().setFragmentResult("key1", result);

        Toast.makeText(getActivity(), "Movie added", Toast.LENGTH_SHORT).show();
        });
    }
}
