package com.hfad.newfilms.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.hfad.newfilms.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FilmsItem.class}, version = 1, exportSchema = false)
public abstract class FilmsRoomDatabase extends RoomDatabase {

    public abstract FilmsDao filmDao();

    private static volatile FilmsRoomDatabase INSTANCE; //синглтон
    private static final int NUMBER_OF_THREADS = 4;

    private static RoomDatabase.Callback fRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                FilmsDao dao = INSTANCE.filmDao();
//                dao.deleteAll();

                FilmsItem film = new FilmsItem(
                        "Pride & Prejudice",
                        "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. " +
                                "Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. " +
                                "Can each overcome their own pride and prejudice?",
                        0,
                        false
                );
                dao.insert(film);
            });
        }
    };

    //для асинхронного выполнения операций с базой данных в фоновом потоке
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FilmsRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FilmsRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            FilmsRoomDatabase.class, "films_database")
                            .addCallback(fRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
