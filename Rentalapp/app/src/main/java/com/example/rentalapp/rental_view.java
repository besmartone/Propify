package com.example.rentalapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class rental_view extends AppCompatActivity {

    private RecyclerView propertiesRecyclerView;
    private PropertyAdapter_user propertyAdapter;
    private List<Property> propertiesList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rental_view);

        propertiesRecyclerView = findViewById(R.id.propertiesRecyclerViewnew);
        propertiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        propertiesList = new ArrayList<>();
        propertyAdapter = new PropertyAdapter_user(this, propertiesList);
        propertiesRecyclerView.setAdapter(propertyAdapter);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Properties").child(currentUser.getUid());
            loadData();
        }
    }

    private void loadData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
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
                Toast.makeText(rental_view.this, "Error loading data: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
