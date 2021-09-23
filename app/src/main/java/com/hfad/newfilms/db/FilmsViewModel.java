package com.hfad.newfilms.db;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hfad.newfilms.FilmsItemRepository;

import java.util.List;
import java.util.Objects;

public class FilmsViewModel extends AndroidViewModel {

    private FilmsItemRepository fRepository;
    private final LiveData<List<FilmsItem>> allFilms;
    private final LiveData<List<FilmsItem>> allFilmsIsLike;
    private final MutableLiveData<FilmsItem> selectedFilm = new MutableLiveData<FilmsItem>();

    //конструктор
    public FilmsViewModel (Application application) {
        super(application);
        fRepository = new FilmsItemRepository(application);
        allFilms = fRepository.getAllFilms();
        allFilmsIsLike = fRepository.getAllFilmsIsLike();
    }


    public void select(FilmsItem filmsItem){
        selectedFilm.setValue(filmsItem);
    }

    public LiveData<FilmsItem> getSelectedFilm(){
        return selectedFilm;
    }


    // метод возврата кэшированого списка фильмов
    public LiveData<List<FilmsItem>> getAllFilms() {
        return allFilms; }

    public LiveData<List<FilmsItem>> getAllFilmsIsLike() {
        return allFilmsIsLike; }

    //метод оболочка метода репозитория insert() - инкапсуляция
    public void insert(FilmsItem film) {
        fRepository.insert(film);
    }

    public void update(FilmsItem film) {fRepository.update(film); }

}
