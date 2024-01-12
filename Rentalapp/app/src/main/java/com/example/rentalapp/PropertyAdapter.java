package com.example.rentalapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PropertyAdapter extends RecyclerView.Adapter<PropertyAdapter.PropertyViewHolder> {

    private List<Property> propertiesList;

    public PropertyAdapter(List<Property> propertiesList) {
        this.propertiesList = propertiesList;
    }

    @Override
    public PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item, parent, false);
        return new PropertyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(PropertyViewHolder holder, int position) {
        Property property = propertiesList.get(position);
        holder.titleTextView.setText(property.getPropertyTitle());
        // Set other properties to the view holder
    }

    @Override
    public int getItemCount() {
        return propertiesList.size();
    }

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        // Other views

        public PropertyViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            // Initialize other views
        }
    }
}
