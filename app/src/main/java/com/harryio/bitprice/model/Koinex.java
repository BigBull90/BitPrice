package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public final class Koinex {

    private KoinexInner prices;

    public String getPrice() {
        return prices.getBTC();
    }

    private class KoinexInner {

        @SerializedName("BTC")
        private String btc;

        private String getBTC() {
            return btc;
        }
    }
}
