package com.example.rentalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PropertyAdapter_user extends RecyclerView.Adapter<PropertyAdapter_user.PropertyViewHolder> {

    private List<Property> propertiesList;
    private Context context;

    public PropertyAdapter_user(Context context, List<Property> propertiesList) {
        this.context = context;
        this.propertiesList = propertiesList;
    }

    @NonNull
    @Override
    public PropertyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_rents, parent, false);
        return new PropertyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PropertyViewHolder holder, int position) {
        Property property = propertiesList.get(position);

        holder.titleTextView.setText(property.getPropertyTitle());
        holder.locationInfoTextView.setText(property.getLocation());
        holder.rentInfoTextView.setText(property.getMonthlyRent());

        // Set click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show pop-up message
                Toast.makeText(context, "Your Request For Rental property has been Sent. Team Will contact Soon", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView locationInfoTextView;
        TextView rentInfoTextView;

        public PropertyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            locationInfoTextView = itemView.findViewById(R.id.locationInfoTextView);
            rentInfoTextView = itemView.findViewById(R.id.rentInfoTextView);
        }
    }
}
