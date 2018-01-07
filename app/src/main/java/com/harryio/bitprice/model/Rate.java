package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public class Rate {

    @SerializedName("INR")
    private float inr;

    public float getInr() {
        return inr;
    }
}
