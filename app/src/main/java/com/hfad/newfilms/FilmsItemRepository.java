package com.hfad.newfilms;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.hfad.newfilms.db.FilmsDao;
import com.hfad.newfilms.db.FilmsItem;
import com.hfad.newfilms.db.FilmsRoomDatabase;

import java.util.ArrayList;
import java.util.List;

public class FilmsItemRepository {

    private FilmsDao fDao;
    private LiveData<List<FilmsItem>> allFilms;
    private LiveData<List<FilmsItem>> filmsIsLike;

    public FilmsItemRepository(Application application) {
        FilmsRoomDatabase db = FilmsRoomDatabase.getDatabase(application);
        fDao = db.filmDao();
        allFilms = fDao.getAllFilms();
        filmsIsLike = fDao.getAllFilmsIsLike();
    }

    //возвращает список фильмов
    public LiveData<List<FilmsItem>> getAllFilms() {
        return allFilms;
    }

    public LiveData<List<FilmsItem>> getAllFilmsIsLike() {
        return filmsIsLike;
    }

    //вставка в фоновом потоке
    public void insert(FilmsItem film) {
        FilmsRoomDatabase.databaseWriteExecutor.execute(() -> fDao.insert(film));
    }

    public void update(FilmsItem film) {
        FilmsRoomDatabase.databaseWriteExecutor.execute(() -> fDao.update(film));
    }
}
