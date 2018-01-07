package com.harryio.bitprice.model;

public final class CoinSecure {

    private CoinSecureInner message;

    public float getPrice() {
        return message.getAsk();
    }

    private class CoinSecureInner {

        private float ask;

        private float getAsk() {
            return ask;
        }
    }
}
