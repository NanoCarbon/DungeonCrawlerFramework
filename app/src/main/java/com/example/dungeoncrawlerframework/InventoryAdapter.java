package com.example.dungeoncrawlerframework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>{

    private ArrayList<Integer> mPlayerInventory;
    private ItemDictionary itemDictionary;
    private int itemIndex;

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

    public InventoryAdapter(ArrayList<Integer> playerInventory) {
        mPlayerInventory = playerInventory;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.inventoryitem,parent,false);
        InventoryViewHolder inventoryViewHolder = new InventoryViewHolder(v);
        return inventoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {


        itemDictionary = new ItemDictionary();
        itemIndex = mPlayerInventory.get(position);

        Item currentItem = itemDictionary.getItem(itemIndex);
        holder.itemImageDisplay.setImageResource(currentItem.getItemImageId());
        holder.itemNameDisplay.setText(currentItem.getItemName());
        holder.itemDescriptionDisplay.setText(currentItem.getItemDescription());
    }

    @Override
    public int getItemCount() {
        return mPlayerInventory.size();
    }
}
