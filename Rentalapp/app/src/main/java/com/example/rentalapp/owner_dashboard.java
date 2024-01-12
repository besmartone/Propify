package com.example.rentalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class owner_dashboard extends AppCompatActivity {

    private RecyclerView propertiesRecyclerView;
    private PropertyAdapter propertyAdapter;
    private List<Property> propertiesList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);

        propertiesRecyclerView = findViewById(R.id.propertiesRecyclerView);
        propertiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        propertiesList = new ArrayList<>();
        propertyAdapter = new PropertyAdapter(propertiesList);
        propertiesRecyclerView.setAdapter(propertyAdapter);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Properties").child(currentUser.getUid());
            loadData();
        }
    }

    private void loadData() {
        databaseReference.addValueEventListener(new ValueEventListener() {


            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                propertiesList.clear();
                for (DataSnapshot propertySnapshot : dataSnapshot.getChildren()) {
                    Property property = propertySnapshot.getValue(Property.class);
                    propertiesList.add(property);
                }
                propertyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(owner_dashboard.this, "Error loading data: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    }
