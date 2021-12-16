package com.emad.familyinfo.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.emad.familyinfo.data.dao.FamilyInfoDao;


@Database(entities = {FamilyInfoModel.class}, version = 1, exportSchema = false)
public abstract class FamilyDatabase extends RoomDatabase {

    public abstract FamilyInfoDao familyInfoDao();

}
