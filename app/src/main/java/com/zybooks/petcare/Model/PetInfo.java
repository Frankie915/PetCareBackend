package com.zybooks.petcare.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PetInfo {

    @PrimaryKey
    @NonNull
    private String mMicrochipID;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "gender")
    private String mGender;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "breed")
    private String mBreed;

    @ColumnInfo(name = "accessCode")
    private long mAccessCode;

    @ColumnInfo(name = "isNeutered")
    private String mIsNeutered;

    public void setMicrochipID(String chipID){mMicrochipID = chipID;}
    public String getMicrochipID(){return mMicrochipID;}

    public void setName(String name){mName = name;}
    public String getName(){return mName;}

    public void setGender(String gender){mGender = gender;}
    public String getGender(){return mGender;}

    public void setEmail(String email){mEmail = email;}
    public String getEmail(){return mEmail;}

    public void setBreed(String breed){mBreed = breed;}
    public String getBreed(){return mBreed;}

    public void setAccessCode(long accessCode){mAccessCode = accessCode;}
    public long getAccessCode(){return mAccessCode;}

    public void setIsNeutered(String isNeutered){mIsNeutered = isNeutered;}
    public String getIsNeutered(){return mIsNeutered;}
}
