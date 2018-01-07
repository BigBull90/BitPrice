package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public final class LocalBitcoins {

    @SerializedName("INR")
    private LocalBitcoinsInner btc;

    public float getPrice() {
        return btc.getPrice();
    }

    private class LocalBitcoinsInner {

        @SerializedName("rates")
        private LocalBitcoinsRate rate;

        private float getPrice() {
            return rate.getPrice();
        }
    }

    private class LocalBitcoinsRate {

        private String last;

        private float getPrice() {
            return Float.valueOf(last);
        }
    }
}
