package com.hfad.newfilms;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.hfad.newfilms.db.FilmsDao;
import com.hfad.newfilms.db.FilmsItem;
import com.hfad.newfilms.db.FilmsRoomDatabase;

import java.util.List;

public class FilmsItemRepository {
//    private static List<FilmsItem> items = new ArrayList<>();
//    private static List<FilmsItem> favoriteFilms = new ArrayList<>();
//    private static FilmsItemRepository instance;

    private FilmsDao fDao;
    private LiveData<List<FilmsItem>> allFilms;

    public FilmsItemRepository(Application application) {
        FilmsRoomDatabase db = FilmsRoomDatabase.getDatabase(application);
        fDao = db.filmDao();
        allFilms = fDao.getAllFilms();
    }

    //возвращает список фильмов
    public LiveData<List<FilmsItem>> getAllFilms() {
        return allFilms;
    }

    //вставка в фоновом потоке
    public void insert(FilmsItem film) {
        FilmsRoomDatabase.databaseWriteExecutor.execute(() -> fDao.insert(film));
    }

//    private FilmsItemRepository(){
//        items.add(new FilmsItem(
//                "Дюна",
//                "Новая вселенная",
//                0,
//                0,
//                false
//        ));
//    }

//    public static FilmsItemRepository getInstance(){
//        if(instance == null){
//            instance = new FilmsItemRepository();
//        }
//        return instance;
//    }

//    public List<FilmsItem> getItems(){
//        return items;
//    }
//
//    public static List<FilmsItem> getFavoriteFilms() {
//        favoriteFilms.clear();
//        for (FilmsItem filmItem: items) {
//            if (filmItem.isLiked) {
//                favoriteFilms.add(filmItem);
//            }
//        }
//        return favoriteFilms;
//    }
}
