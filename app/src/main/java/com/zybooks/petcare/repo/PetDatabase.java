package com.zybooks.petcare.repo;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.zybooks.petcare.Model.PetInfo;

@Database(entities = {PetInfo.class}, version = 2)
public abstract class PetDatabase extends RoomDatabase {

public abstract PetInfoDao petInfoDao();
}
