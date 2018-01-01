package com.harryio.bitprice.model;

public class CoinSecureWrapper {

    private boolean success;
    private Coinsecure message;

    public boolean isSuccess() {
        return success;
    }

    public Coinsecure getData() {
        return message;
    }
}
