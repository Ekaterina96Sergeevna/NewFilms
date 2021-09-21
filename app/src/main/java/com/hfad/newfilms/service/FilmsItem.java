package com.hfad.newfilms.service;

import com.hfad.newfilms.service.FilmsJson;

public class FilmsItem {
    public String name;
    public String description;
    public String imageUrl;
    public int imageResourseId;
    public int itemId;
    public boolean isLiked;

    public FilmsItem(FilmsJson filmJson, boolean isLiked, int filmId) {
        this.name = filmJson.name;
        this.description = filmJson.description;
        this.imageUrl = filmJson.img;
        this.isLiked = isLiked;
        this.itemId = filmId;
    }


    public FilmsItem(String name, String description, int imageResourseId, int itemId, boolean isLiked) {
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
        this.itemId = itemId;
        this.isLiked = isLiked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
