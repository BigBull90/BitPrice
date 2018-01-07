package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public final class Rate {

    @SerializedName("rates")
    private RateInner rate;

    public float getInr() {
        return rate.getInr();
    }

    private class RateInner {

        @SerializedName("INR")
        private float inr;

        private float getInr() {
            return inr;
        }
    }
}
