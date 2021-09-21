package com.hfad.newfilms;

import com.hfad.newfilms.service.FilmsItem;

import java.util.ArrayList;
import java.util.List;

public class FilmsItemRepository {
    private static List<FilmsItem> items = new ArrayList<>();
    private static List<FilmsItem> favoriteFilms = new ArrayList<>();

    private static FilmsItemRepository instance;

    private FilmsItemRepository(){
        items.add(new FilmsItem(
                "Дюна",
                "Новая вселенная",
                0,
                0,
                false
        ));
    }

    public static FilmsItemRepository getInstance(){
        if(instance == null){
            instance = new FilmsItemRepository();
        }
        return instance;
    }

    public List<FilmsItem> getItems(){
        return items;
    }

    public static List<FilmsItem> getFavoriteFilms() {
        favoriteFilms.clear();
        for (FilmsItem filmItem: items) {
            if (filmItem.isLiked) {
                favoriteFilms.add(filmItem);
            }
        }
        return favoriteFilms;
    }
}
