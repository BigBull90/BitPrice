package com.harryio.bitprice.model;

import com.google.gson.annotations.SerializedName;

public class RateWrapper {

    @SerializedName("rates")
    private Rate rate;

    public Rate getRate() {
        return rate;
    }
}
