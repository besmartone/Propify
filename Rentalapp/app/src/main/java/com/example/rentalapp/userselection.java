package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class userselection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userselection);
        CardView owner = findViewById(R.id.cv_admin);
        CardView renter = findViewById(R.id.cv_user);

        owner.setOnClickListener(view -> {
            Intent act_login_owner = new Intent(userselection.this , ownerlogin.class);
            startActivity(act_login_owner);
        });
        renter.setOnClickListener(view -> {
            Intent act_login_renter = new Intent(userselection.this,renterlogin.class);
            startActivity(act_login_renter);
        });
    }
}