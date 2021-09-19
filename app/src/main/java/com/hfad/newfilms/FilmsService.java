package com.hfad.newfilms;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsService {
    @GET("films")
    Call<List<FilmsJson>> getFilm();
}
