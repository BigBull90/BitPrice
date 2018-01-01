package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public final class Koinex {

    @SerializedName("BTC")
    private String btc;

    public String getBTC() {
        return btc;
    }
}
