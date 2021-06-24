package com.hfad.newfilms;

import java.util.ArrayList;
import java.util.List;

public class FilmsItemRepository {
    private static List<FilmsItem> items = new ArrayList<>();
    private static List<FilmsItem> favoriteFilms = new ArrayList<>();

    private static FilmsItemRepository instance;

    private FilmsItemRepository(){
        for (int i = 0; i < FilmsItem.films.length; i++) {
            items.add(new FilmsItem(FilmsItem.films[i].name, FilmsItem.films[i].imageResourseId,
                    i, FilmsItem.films[i].isLiked));
        }
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
