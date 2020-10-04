package com.example.dungeoncrawlerframework;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>{

    public static class InventoryViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImageDisplay;
        public TextView itemNameDisplay;
        public TextView itemDescriptionDisplay;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageDisplay = itemView.findViewById(R.id.inventoryItemImageView);
            itemNameDisplay = itemView.findViewById(R.id.inventoryItemNameTextView);
            itemDescriptionDisplay = itemView.findViewById(R.id.inventoryItemDescriptionTextView);
        }
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
