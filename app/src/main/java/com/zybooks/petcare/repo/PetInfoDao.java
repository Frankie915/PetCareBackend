package com.zybooks.petcare.repo;



import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.zybooks.petcare.Model.PetInfo;
import java.util.List;

@Dao
public interface PetInfoDao {

    @Query("SELECT mMicrochipID FROM PetInfo")
    List<String> getIDs();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addRegistration(PetInfo registration);
//testing

}

