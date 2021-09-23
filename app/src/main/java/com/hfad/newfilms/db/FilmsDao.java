package com.hfad.newfilms.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FilmsDao {
    @Insert
    void insert(FilmsItem film);

    @Update
    void update(FilmsItem film);

    //удаление всех фильмов
    @Query("DELETE FROM films_table")
    void deleteAll();

    @Query("SELECT * FROM films_table")
    LiveData<List<FilmsItem>> getAllFilms();

    @Query("SELECT * FROM films_table WHERE isLiked = 1")
    LiveData<List<FilmsItem>> getAllFilmsIsLike();
}
