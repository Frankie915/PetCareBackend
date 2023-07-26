package com.zybooks.petcare;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import com.zybooks.petcare.Model.PetInfo;
import com.zybooks.petcare.repo.PetRepository;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PetRegistration PetReg;
    private Spinner spinner;
    private TextView Microchip;
    private EditText EditMicrochip;

    private EditText EditName;
    private RadioButton RadButton;
    private RadioGroup GenderRadioGroup;

    private String Gender;
    private EditText EditEmail;
    private TextView Email;
    private EditText EditAccessCode;
    private TextView AccessCode;
    private EditText EditConfirmCode;

    private TextView ConfirmCode;
    private CheckBox IsNeutered;
    private String Neutered;
    private List<String> IDs;

    private PetRepository mPetRepo;
    private int colorRed, colorGray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative);

        PetReg = new PetRegistration();

        mPetRepo = PetRepository.getInstance(getApplicationContext());
        IDs = getIDs();

        colorRed = getColor(R.color.red); //error color
        colorGray = getColor(R.color.gray); //default color

        EditMicrochip = findViewById(R.id.edit_microchip);
        Microchip = findViewById(R.id.microchip_id);
        EditName = findViewById(R.id.edit_name);

        RadButton = findViewById(R.id.radio_female);
        RadButton.setChecked(true);
        GenderRadioGroup = findViewById(R.id.gender_group);
        EditEmail = findViewById(R.id.edit_email_address);
        Email = findViewById(R.id.email_address);
        EditAccessCode = findViewById(R.id.edit_access_code);
        AccessCode = findViewById(R.id.access_code);
        ConfirmCode = findViewById(R.id.confirm_code);
        EditConfirmCode = findViewById(R.id.edit_confirm_code);
        IsNeutered = findViewById(R.id.is_neutered);

        spinner = findViewById(R.id.select_breed);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.breed_array, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        IsNeutered.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked)
                Neutered = "Yes";
            else
                Neutered = "No";
        });

        GenderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radio_male) {
                RadButton = findViewById(R.id.radio_male);
                Gender = "Male";
            }
            else {
                RadButton = findViewById(R.id.radio_female);
                Gender = "Female";
            }
        });
    }

    public List<String> getIDs() {
        return mPetRepo.getIDs();
    }

    public void addPetForm(PetInfo petForm) {
        mPetRepo.addPetForm(petForm);
    }

    public void ResetClicked(View view) {

        EditMicrochip.setText("");
        EditName.setText("");
        RadButton = findViewById(R.id.radio_female);
        RadButton.setChecked(true);
        EditEmail.setText("");
        //Email.setTextColor(colorGray);
        EditAccessCode.setText("");
        AccessCode.setTextColor(colorGray);
        EditConfirmCode.setText("");
        ConfirmCode.setTextColor(colorGray);
        IsNeutered = findViewById(R.id.is_neutered);
        IsNeutered.setChecked(true);
        spinner.setSelection(0);
    }

    public void SubmitClick(View view) {
        boolean AllConditionsMet= true;

        String microchip = EditMicrochip.getText().toString();
        if(PetReg.checkMicrochip(microchip, IDs))
            Microchip.setTextColor(colorGray);
        else {
            //Microchip.setTextColor(colorRed);
            AllConditionsMet = false;
            Toast.makeText(this, "Error: Chip already exists", Toast.LENGTH_SHORT).show();
        }
        String email = EditEmail.getText().toString();

        if(PetReg.checkEmail(email)) {
            Email.setTextColor(colorGray);
        }
        else {
            Email.setTextColor(colorRed);
            AllConditionsMet = false;
            //Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();
        }

        int accessCode = 0, confirmCode=0;

        String EAccessCode = EditAccessCode.getText().toString();
        String EConfirmCode = EditConfirmCode.getText().toString();
        try {

            accessCode = Integer.parseInt(EAccessCode);
            confirmCode = Integer.parseInt(EConfirmCode);
        }
        catch (NumberFormatException x) {}


        if(EAccessCode.isEmpty() || EConfirmCode.isEmpty()) {
            AccessCode.setTextColor(colorRed);
            ConfirmCode.setTextColor(colorRed);
            AllConditionsMet = false;
            //Toast.makeText(this, "Codes do no match", Toast.LENGTH_SHORT).show();
        }
        else if(PetReg.checkCode(accessCode, confirmCode))
        {
            AccessCode.setTextColor(colorGray);
            ConfirmCode.setTextColor(colorGray);
        }

        else
        {
            AccessCode.setTextColor(colorRed);
            ConfirmCode.setTextColor(colorRed);
            //Toast.makeText(this, "Codes do no match", Toast.LENGTH_SHORT).show();
            AllConditionsMet = false;
        }
        if(AllConditionsMet)
        {

            PetInfo petForm = new PetInfo();
            petForm.setMicrochipID(EditMicrochip.getText().toString());
            petForm.setName(EditName.getText().toString());
            petForm.setGender(Gender);
            petForm.setBreed(spinner.getSelectedItem().toString());
            petForm.setEmail(EditEmail.getText().toString());
            petForm.setAccessCode(Long.parseLong(EditAccessCode.getText().toString()));
            petForm.setIsNeutered(Neutered);
            addPetForm(petForm);
            Toast.makeText(this, "Data has been saved!", Toast.LENGTH_SHORT).show();
            IDs = getIDs();
        }



    }

}