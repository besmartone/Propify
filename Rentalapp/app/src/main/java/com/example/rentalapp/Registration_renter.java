package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;
import android.util.Patterns;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;

public class Registration_renter extends AppCompatActivity {

    private EditText fullName, email, password, mobileNumber;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_renter);

        fullName = findViewById(R.id.id_fullname_newuser);
        email = findViewById(R.id.id_email_newuser);
        password = findViewById(R.id.id_password_newuser);
        mobileNumber = findViewById(R.id.id_mobileno_newuser);
        registerButton = findViewById(R.id.btn_regitser);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String fullNameText = fullName.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String mobileNumberText = mobileNumber.getText().toString().trim();

        // Validation checks
        if (fullNameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty() || mobileNumberText.isEmpty()) {
            Toast.makeText(Registration_renter.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            email.setError("Please enter a valid email");
            email.requestFocus();
            return;
        }

        if (passwordText.length() < 6) {
            password.setError("Minimum password length should be 6 characters");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // User registration success
                        String userId = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

                        // Create a user object in the database
                        User newUser = new User(fullNameText, emailText, mobileNumberText);
                        databaseReference.child(userId).setValue(newUser);

                        // Navigate to the next activity or perform other actions
                        Toast.makeText(Registration_renter.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Registration_renter.this, reg_successful.class);
                        intent.putExtra("name", fullNameText);
                        intent.putExtra("email", emailText);
                        startActivity(intent);
                    } else {
                        // If registration fails, display a message to the user.
                        Toast.makeText(Registration_renter.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
