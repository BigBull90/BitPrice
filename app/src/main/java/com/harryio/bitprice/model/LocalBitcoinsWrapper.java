package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public class LocalBitcoinsWrapper {

    @SerializedName("INR")
    private LocalBitcoins btc;

    public LocalBitcoins getBtc() {
        return btc;
    }
}
