package com.emad.familyinfo.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.emad.familyinfo.data.FamilyInfoModel;

import java.util.List;

@Dao
public interface FamilyInfoDao {

    @Query("SELECT COUNT(*) FROM family_table")
    int getCount();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(FamilyInfoModel info);

    @Update
    void update(FamilyInfoModel info);

    @Delete
    void deleteProfile(FamilyInfoModel info);

    @Query("SELECT * FROM family_table")
    LiveData<List<FamilyInfoModel>> fetchAllData();
}
