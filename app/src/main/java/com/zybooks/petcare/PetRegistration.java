package com.zybooks.petcare;

import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PetRegistration {
    public boolean checkMicrochip(String microchip, List<String> list)
    {
        if(!regex(microchip, "^[A-Z0-9]{5,15}$"))
        {
            return false;
        }

        for(String str: list)
            if (str.equals(microchip))
                return false;

        return true;
    }
    public boolean regex(String input, String re){
        Pattern pt = Pattern.compile(re);
        Matcher mt = pt.matcher(input);
        return mt.matches();
    }

    public boolean checkEmail(String email){
        System.out.println(email);
        return regex(email, "^[A-Za-z0-9._%+-]{3,}@[A-Za-z0-9.-]+\\.(?:edu|co|com|gal)$");
    }

    public boolean checkCode(int accessCode, int confirmCode) {
        if(accessCode != confirmCode)
            return false;
        else
            return true;
    }


}

