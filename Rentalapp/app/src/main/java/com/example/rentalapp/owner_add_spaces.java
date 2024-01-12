package com.example.rentalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class owner_add_spaces extends AppCompatActivity {

    private EditText propertyTitleEditText, numBedroomsEditText, numBathroomsEditText,
            monthlyRentEditText, locationEditText, contactNumberEditText;
    private Spinner propertyTypeSpinner;
    private Button submitBtn;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_spaces);

        propertyTypeSpinner = findViewById(R.id.propertyTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.property_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        propertyTypeSpinner.setAdapter(adapter);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // Redirect to login or handle the user not logged in
            return; // Make sure to handle this scenario appropriately.
        }

        String userId = currentUser.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Properties").child(userId);

        propertyTitleEditText = findViewById(R.id.propertyTitleEditText);
        numBedroomsEditText = findViewById(R.id.numBedroomsEditText);
        numBathroomsEditText = findViewById(R.id.numBathroomsEditText);
        monthlyRentEditText = findViewById(R.id.monthlyRentEditText);
        locationEditText = findViewById(R.id.locationEditText);
        contactNumberEditText = findViewById(R.id.contactNumberEditText);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPropertyDetails();
            }
        });
    }

    private void submitPropertyDetails() {
        String propertyTitle = propertyTitleEditText.getText().toString().trim();
        String propertyType = propertyTypeSpinner.getSelectedItem().toString();
        String numBedrooms = numBedroomsEditText.getText().toString().trim();
        String numBathrooms = numBathroomsEditText.getText().toString().trim();
        String monthlyRent = monthlyRentEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();
        String contactNumber = contactNumberEditText.getText().toString().trim();

        if (TextUtils.isEmpty(propertyTitle) || TextUtils.isEmpty(propertyType) ||
                TextUtils.isEmpty(numBedrooms) || TextUtils.isEmpty(numBathrooms) ||
                TextUtils.isEmpty(monthlyRent) || TextUtils.isEmpty(location) ||
                TextUtils.isEmpty(contactNumber)) {

            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        Property property = new Property(propertyTitle, propertyType, numBedrooms,
                numBathrooms, monthlyRent, location, contactNumber);

        // Push the property details to Firebase
        databaseReference.push().setValue(property)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(owner_add_spaces.this, "Property details submitted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),owner_dashboard.class);
                    startActivity(intent);
                    clearForm();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(owner_add_spaces.this, "Failed to submit property details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    private void clearForm() {
        propertyTitleEditText.setText("");
        numBedroomsEditText.setText("");
        numBathroomsEditText.setText("");
        monthlyRentEditText.setText("");
        locationEditText.setText("");
        contactNumberEditText.setText("");
        propertyTypeSpinner.setSelection(0);
    }
}
