package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public class LocalBitcoins {

    @SerializedName("rates")
    private LocalBitcoinRate rate;

    public float getPrice() {
        return rate.getPrice();
    }

    private class LocalBitcoinRate {
        private String last;

        public float getPrice() {
            return Float.valueOf(last);
        }
    }
}
