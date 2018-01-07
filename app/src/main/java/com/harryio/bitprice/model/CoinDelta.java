package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public final class CoinDelta {

    @SerializedName("Ask")
    private float price;
    @SerializedName("MarketName")
    private String marketName;

    public float getPrice() {
        return price;
    }

    public String getMarketName() {
        return marketName;
    }
}
