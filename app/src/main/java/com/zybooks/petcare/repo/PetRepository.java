package com.zybooks.petcare.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import com.zybooks.petcare.Model.PetInfo;
import java.util.List;
public class PetRepository {

    private static PetRepository mPetRepo;
    private final PetInfoDao mPetInfoDao;

    public static PetRepository getInstance(Context context) {
        if (mPetRepo == null) {
            mPetRepo = new PetRepository(context);
        }
        return mPetRepo;
    }

    private PetRepository(Context context) {
        PetDatabase database = Room.databaseBuilder(context, PetDatabase.class, "pet4.db")
                .allowMainThreadQueries()
                .build();

        mPetInfoDao = database.petInfoDao();


        if (mPetInfoDao.getIDs().isEmpty()) {
            addStarterData();
        }
    }

    private void addStarterData() {
        PetInfo petForm = new PetInfo();
        petForm.setMicrochipID("K39BD3L");
        mPetInfoDao.addRegistration(petForm);

        petForm = new PetInfo();
        petForm.setMicrochipID("MM30R54");
       mPetInfoDao.addRegistration(petForm);

        petForm = new PetInfo();
        petForm.setMicrochipID("Q94JFFD9");
        mPetInfoDao.addRegistration(petForm);

        petForm = new PetInfo();
        petForm.setMicrochipID("Q945KYL");
        mPetInfoDao.addRegistration(petForm);

        petForm = new PetInfo();
        petForm.setMicrochipID("09NFEWN23D");
        mPetInfoDao.addRegistration(petForm);
    }

    public void addPetForm(PetInfo form) {
        mPetInfoDao.addRegistration(form);
    }

    public List<String> getIDs() {
        return mPetInfoDao.getIDs();
    }

}
