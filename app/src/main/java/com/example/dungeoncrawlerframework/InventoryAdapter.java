package com.example.dungeoncrawlerframework;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;


public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder>{

    private ArrayList<Integer> uniquePlayerInventory;
    private ItemDictionary itemDictionary;
    private int itemIndex;
    private ArrayList<Integer> mPlayerInventory;

    public static class InventoryViewHolder extends RecyclerView.ViewHolder{
        public ImageView itemImageDisplay;
        public TextView itemNameDisplay;
        public TextView itemDescriptionDisplay;
        public TextView itemCountDisplay;

        public InventoryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImageDisplay = itemView.findViewById(R.id.inventoryItemImageView);
            itemNameDisplay = itemView.findViewById(R.id.inventoryItemNameTextView);
            itemDescriptionDisplay = itemView.findViewById(R.id.inventoryItemDescriptionTextView);
            itemCountDisplay = itemView.findViewById(R.id.inventoryItemCountTextView);
        }
    }

    public InventoryAdapter(ArrayList<Integer> playerInventory) {
        uniquePlayerInventory = playerInventory;
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

        itemIndex = uniquePlayerInventory.get(position);

        Item currentItem = itemDictionary.getItem(itemIndex);
        holder.itemImageDisplay.setImageResource(currentItem.getItemImageId());
        holder.itemNameDisplay.setText(currentItem.getItemName());
        holder.itemDescriptionDisplay.setText(currentItem.getItemDescription());
        holder.itemCountDisplay.setText(Integer.toString(Collections.frequency(mPlayerInventory,itemIndex)));
    }

    @Override
    public int getItemCount() {
        return uniquePlayerInventory.size();
    }

    public void setPlayerInventory(ArrayList<Integer> playerInventory){
        mPlayerInventory = playerInventory;
    };

}
