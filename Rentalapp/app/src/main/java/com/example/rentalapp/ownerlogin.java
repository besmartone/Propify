package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ownerlogin extends AppCompatActivity {

    private EditText email, password;
    private Button SignInbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ownerlogin);

        email = findViewById(R.id.email_et);
        password = findViewById(R.id.password_et);
        SignInbtn = findViewById(R.id.btn_sign_in);

        SignInbtn.setOnClickListener(v -> {
            login();
        });
    }

    private void login() {
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();
        if (!TextUtils.isEmpty(email1) && !TextUtils.isEmpty(password1)) {
            if (email1.equals("admin") && password1.equals("Admin")) {
                Intent intent = new Intent(ownerlogin.this, ownerView.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Please enter correct Email and Password", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter Email and password", Toast.LENGTH_SHORT).show();
        }
    }
}
