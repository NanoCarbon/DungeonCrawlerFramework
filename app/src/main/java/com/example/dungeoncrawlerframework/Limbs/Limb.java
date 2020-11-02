package com.example.dungeoncrawlerframework.Limbs;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.dungeoncrawlerframework.Items.Item;

public class Limb implements Parcelable {


    private Item equippedItem;
    private int limbType;

    public Limb(int limbType){
        this.limbType = limbType;
    }

    protected Limb(Parcel in) {
        limbType = in.readInt();
        equippedItem = in.readParcelable(Item.class.getClassLoader());
    }

    public static final Creator<Limb> CREATOR = new Creator<Limb>() {
        @Override
        public Limb createFromParcel(Parcel in) {
            return new Limb(in);
        }

        @Override
        public Limb[] newArray(int size) {
            return new Limb[size];
        }
    };

    public Item getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(Item equippedItem) {
        this.equippedItem = equippedItem;
    }

    public int getLimbType() {
        return limbType;
    }

    public void setLimbType(int limbType) {
        this.limbType = limbType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(limbType);
        dest.writeParcelable(equippedItem,flags);
    }
}
