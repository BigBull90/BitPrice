package com.harryio.bitprice.model;

public final class BitcoinPrice {

    private String source;
    private String price;
    private boolean error;

    private BitcoinPrice() {
        error = true;
    }

    private BitcoinPrice(String source, String price) {
        this.source = source;
        this.price = price;
    }

    public static BitcoinPrice forValue(String source, String price) {
        return new BitcoinPrice(source, price);
    }

    public static BitcoinPrice forError() {
        return new BitcoinPrice();
    }

    public String getSource() {
        return source;
    }

    public String getPrice() {
        return price;
    }

    public boolean isError() {
        return error;
    }
}