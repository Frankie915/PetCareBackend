package com.zybooks.petcare;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetRegistration {

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

