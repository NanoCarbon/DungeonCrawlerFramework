package com.example.dungeoncrawlerframework.Items;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dungeoncrawlerframework.Limbs.Limb;

public class Item implements Parcelable {


    private int itemName;
    private int itemImageId;
    private int effectValue;
    private int itemEffectType;
    private boolean equippable;
    private String itemDescription;
    private int limbRestriction;
    //=============================CONSTRUCTOR====================================//
    public Item(int itemName, int itemImageId, int effectValue, int itemEffectType,
                boolean equippable, String itemDescription, int limbRestriction){
        this.itemName = itemName;
        this.itemImageId = itemImageId;
        this.effectValue = effectValue;
        this.itemEffectType = itemEffectType;
        this.equippable = equippable;
        this.itemDescription = itemDescription;
        this.limbRestriction = limbRestriction;
    }
    //=============================CONSTRUCTOR====================================//

    protected Item(Parcel in) {
        itemName = in.readInt();
        itemImageId = in.readInt();
        effectValue = in.readInt();
        itemEffectType = in.readInt();
        equippable = in.readByte() != 0;
        itemDescription = in.readString();
        limbRestriction = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    //===========================GETTERS AND SETTERS================================//
    public int getItemName() {
        return itemName;
    }

    public void setItemName(int itemName) {
        this.itemName = itemName;
    }

    public int getItemImageId() {
        return itemImageId;
    }

    public void setItemImageId(int itemImageId) {
        this.itemImageId = itemImageId;
    }

    public int getEffectValue() {
        return effectValue;
    }

    public void setEffectValue(int effectValue) {
        this.effectValue = effectValue;
    }

    public int getItemEffectType() {
            return itemEffectType;
    }

    public void setItemEffectType(int itemEffectType) {
        this.itemEffectType = itemEffectType;
    }

    public boolean isEquippable() {
        return equippable;
    }

    public void setEquippable(boolean equippable) {
        this.equippable = equippable;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getLimbRestriction() {
        return limbRestriction;
    }

    public void setLimbRestriction(int limbRestriction) {
        this.limbRestriction = limbRestriction;
    }
    //===========================GETTERS AND SETTERS================================//



    //====================MAIN ITEM METHODS=========================================//
    public void equipItem(Limb playerLimb){
        if (this.equippable = true && limbRestriction == playerLimb.getLimbType()){
            playerLimb.setEquippedItem(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(itemName);
        dest.writeInt(itemImageId);
        dest.writeInt(effectValue);
        dest.writeInt(itemEffectType);
        dest.writeByte((byte) (equippable ? 1 : 0));
        dest.writeString(itemDescription);
        dest.writeInt(limbRestriction);
    }
    //====================MAIN ITEM METHODS=========================================//
}
