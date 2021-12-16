package com.emad.familyinfo.data;

import android.content.Context;

import androidx.room.Room;

public class RepositoryManagerLocal {

    private static RepositoryManagerLocal instance;

    private FamilyDatabase database;

    public static RepositoryManagerLocal newInstance(Context context) {
        if (instance == null)
            instance = new RepositoryManagerLocal(context);
        return instance;
    }

    private RepositoryManagerLocal(Context context) {
        database = Room.databaseBuilder(context
                , FamilyDatabase.class
                , "mehr")
                .allowMainThreadQueries()
                .build();
    }

    public FamilyDatabase getDatabase() {
        return database;
    }

}
