package com.hfad.newfilms.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.hfad.newfilms.service.FilmsJson;

@Entity(tableName = "films_table")
public class FilmsItem {
    public String name;
    public String description;
    //public String imageUrl;
    public int imageResourseId;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int itemId;
    public boolean isLiked;

    public FilmsItem(String name, String description, int imageResourseId, boolean isLiked) {
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


//    public FilmsItem(FilmsJson filmJson, boolean isLiked, int filmId) {
//        this.name = filmJson.name;
//        this.description = filmJson.description;
//        this.imageUrl = filmJson.img;
//        this.isLiked = isLiked;
//        this.itemId = filmId;
//    }
}
