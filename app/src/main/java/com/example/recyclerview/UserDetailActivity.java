package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailActivity extends AppCompatActivity {
    TextView userNameTxt, genderTxt, dobTxt, countryTxt, phoneTxt, emailTxt;
    ImageView individualUserImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        individualUserImage= findViewById(R.id.ivUserImg);


        userNameTxt = findViewById(R.id.displayname);
        genderTxt = findViewById(R.id.displaygender);
        dobTxt = findViewById(R.id.displaydob);
        countryTxt = findViewById(R.id.displaycountry);
        phoneTxt = findViewById(R.id.displayphone);
        emailTxt = findViewById(R.id.displayemail);

        Intent intent = getIntent();
        String name= intent.getStringExtra("userName");
        String gender= intent.getStringExtra("gender");
        String dob = intent.getStringExtra("birthDate");
        String country= intent.getStringExtra("country");
        String phone= intent.getStringExtra("phone");
        String email= intent.getStringExtra("email");
        String image= intent.getStringExtra("image");


        String uri = "@drawable/" + image;
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());

        individualUserImage.setImageResource(imageResource);


        userNameTxt.setText("Name :"+name);
        genderTxt.setText("Gender :"+gender);
        dobTxt.setText("DOB :"+dob);
        countryTxt.setText("Country :"+country);
        phoneTxt.setText("Phone :"+phone);
        emailTxt.setText("Email :"+email);
    }
}
