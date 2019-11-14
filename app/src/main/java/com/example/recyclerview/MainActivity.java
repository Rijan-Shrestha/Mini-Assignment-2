package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recyclerview.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{


    EditText nameTxt, emailTxt, dateTxt, phoneTxt;
    RadioGroup genderRadio;
    Spinner spin;
    Button submitBtn, viewBtn;
    String userName, gender, birthDate, country, email, image, phone;
    AutoCompleteTextView autoCompleteTextView;

    ArrayList<User> usersList = new ArrayList<>();


    String[] imagesuggests = {"picture1", "picture2", "picture3", "picture4"};

    Calendar calendardata = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendardata.set(Calendar.YEAR,i);
            calendardata.set(Calendar.MONTH,i1);
            calendardata.set(Calendar.DAY_OF_MONTH,i2);

            String mydateFormat ="dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            dateTxt.setText(simpleDateFormat.format(calendardata.getTime()));


        }

    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        autoCompleteTextView=findViewById(R.id.autocompletetextview);
        nameTxt =findViewById(R.id.name);
        emailTxt =findViewById(R.id.email);
        dateTxt =findViewById(R.id.date);
        phoneTxt =findViewById(R.id.phone);
        genderRadio =findViewById(R.id.gender);
        spin=findViewById(R.id.spCountry);

        submitBtn =findViewById(R.id.btnsubmit);
        viewBtn =findViewById(R.id.btnview);

        List<String> countries = new ArrayList<>();


        countries.add(0,"Choose");
        countries.add("Nepal");
        countries.add("India");
        countries.add("Pakistan");
        countries.add("Bhutan");

        ArrayAdapter<String> adapter =new ArrayAdapter(this,R.layout.spinner_values,countries);
        spin.setAdapter(adapter);

        ArrayAdapter<String> dummyimageadapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, imagesuggests);
        autoCompleteTextView = findViewById(R.id.autocompletetextview);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(dummyimageadapter);



        genderRadio.setOnCheckedChangeListener(this);
        submitBtn.setOnClickListener(this);
        viewBtn.setOnClickListener(this);
        dateTxt.setOnClickListener(this);

        setSpinnerValue();
    }

    @Override
    public void onClick(View view) {
        userName = nameTxt.getText().toString();
        birthDate = dateTxt.getText().toString();
        image=autoCompleteTextView.getText().toString();
        email= emailTxt.getText().toString();
        phone = phoneTxt.getText().toString();



        if(view.getId()==R.id.btnsubmit)
        {
            if(validate())
            {
                usersList.add(new User(userName, gender, birthDate,country,phone,email,image));
//                    Toast.makeText(this,userList.get(0).getName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "submitted", Toast.LENGTH_SHORT).show();
            }




        }

        if(view.getId()==R.id.date)
        {
            new DatePickerDialog(this,mydatepicker,calendardata.get(Calendar.YEAR),calendardata.get(Calendar.MONTH),
                    calendardata.get(Calendar.DAY_OF_MONTH)).show();
        }

        if(view.getId()==R.id.btnview)
        {

//
            Intent intent = new Intent(this, ViewAllUserDetails.class);

            intent.putExtra("userlist",usersList);


            startActivity(intent);

        }


    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i== R.id.rmale)
        {
            gender ="Male";
            //Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
        }
        if(i == R.id.rfemale)
        {
            gender = "Female";
            //Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
        }
        if(i== R.id.rother)
        {
            gender ="Other";
            //Toast.makeText(this, "Other", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validate(){
        if(TextUtils.isEmpty(userName))
        {
            nameTxt.setError("Please enter a valid name");
            nameTxt.requestFocus();
            nameTxt.setHint("Please enter your name");
            return false;
        }
        if(TextUtils.isEmpty(birthDate))
        {
            dateTxt.setError("Enter your date of birth");
            dateTxt.requestFocus();
            dateTxt.setHint("Please Enter a DOB");
            return false;
        }

        if(TextUtils.isEmpty(image))
        {
            autoCompleteTextView.setError("Image name not entered");
            autoCompleteTextView.requestFocus();
            autoCompleteTextView.setHint("Please enter an image");
            return false;
        }

        if(TextUtils.isEmpty(email))
        {
            emailTxt.setError("Enter an email");
            emailTxt.requestFocus();
            emailTxt.setHint("Please Enter your Email");
            return false;
        }

        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(country))
        {
            Toast.makeText(this, "Please select your country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phone))
        {
            phoneTxt.setError("Enter empty Phone");
            phoneTxt.requestFocus();
            phoneTxt.setHint("Please Enter a Phone");
            return false;
        }

        if(!TextUtils.isDigitsOnly(phone))
        {
            phoneTxt.setError("Invalid Phone number");
            phoneTxt.requestFocus();
            phoneTxt.setHint("Please a valid phone number");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailTxt.setError("Invalid  Email");
            emailTxt.requestFocus();
            emailTxt.setHint("Please enter a valid Email");
            return false;
        }

        return  true;
    }


    private void setSpinnerValue(){

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Choose Country"))
                {
                    Toast.makeText(getApplicationContext(),"Please select your country",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    country = adapterView.getSelectedItem().toString();

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }

}
