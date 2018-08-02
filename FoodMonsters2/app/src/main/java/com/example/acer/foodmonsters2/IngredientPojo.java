package com.example.acer.foodmonsters2;

import android.os.Parcel;
import android.os.Parcelable;

public class IngredientPojo implements Parcelable {
    public static final Creator<IngredientPojo> CREATOR = new Creator<IngredientPojo>() {
        @Override
        public IngredientPojo createFromParcel(Parcel in) {
            return new IngredientPojo(in);
        }

        @Override
        public IngredientPojo[] newArray(int size) {
            return new IngredientPojo[size];
        }
    };
    double quantity;
    String measure, ingredient;


    public IngredientPojo(double fquantity, String fmeasure, String fingredient) {
        this.ingredient = fingredient;
        this.measure = fmeasure;
        this.quantity = fquantity;

    }

    protected IngredientPojo(Parcel in) {
        quantity = in.readDouble();
        measure = in.readString();
        ingredient = in.readString();
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(quantity);
        dest.writeString(measure);
        dest.writeString(ingredient);
    }
}
