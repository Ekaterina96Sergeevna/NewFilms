package com.hfad.newfilms;

public class FilmsItem {
    public String name;
    public String description;
    public R draw;
    public int imageResourseId;
    public int itemId;
    public boolean isLiked;

    public FilmsItem(String name, String description, int imageResourseId, int itemId, boolean isLiked) {
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
        this.itemId = itemId;
        this.isLiked = isLiked;
    }

    public FilmsItem(String name, int imageResourseId, int itemId, boolean isLiked) {
        this.name = name;
        this.imageResourseId = imageResourseId;
        this.itemId = itemId;
        this.isLiked = isLiked;
    }


    public FilmsItem(String name, String description, int imageResourseId) {
        this.name = name;
        this.description = description;
        this.imageResourseId = imageResourseId;
    }

    public static final FilmsItem[] films = {
            new FilmsItem("Pride & Prejudice 8.0*",
                    "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. " +
                            "But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. " +
                            "Can each overcome their own pride and prejudice?",
                    R.drawable.prideprejudice),
            new FilmsItem("The Duchess 7.5*",
                    "A chronicle of the life of 18th-century aristocrat Georgiana, " +
                            "Duchess of Devonshire, who was reviled for her extravagant political and personal life.",
                    R.drawable.theduchess),
            new FilmsItem("Anna Karenina 6.8*",
                    "In late-19th-century Russian high society, " +
                            "St. Petersburg aristocrat Anna Karenina enters into a life-changing affair with the dashing Count Alexei Vronsky.",
                    R.drawable.annakarenina),
            new FilmsItem("Seeking a Friend for the End of the World 6.6*",
                    "As an asteroid nears Earth, a man finds himself alone after his wife leaves in a panic. " +
                            "He decides to take a road trip to reunite with his high school sweetheart. " +
                            "Accompanying him is a neighbor who inadvertently puts a wrench in his plan.",
                    R.drawable.jusquaceque)
    };

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResourseId() {
        return imageResourseId;
    }

    public int getItemId() {
        return itemId;
    }

    public boolean isLiked() {
        return isLiked;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
