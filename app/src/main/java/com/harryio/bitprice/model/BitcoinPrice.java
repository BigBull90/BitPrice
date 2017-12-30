package com.harryio.bitprice.model;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class BitcoinPrice {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    public @interface SourceType {

    }

    @SourceType
    private String source;
    private String price;
    private boolean error;

    private BitcoinPrice() {
        error = true;
    }

    private BitcoinPrice(@SourceType String source, String price) {
        this.source = source;
        this.price = price;
    }

    public static BitcoinPrice forValue(@SourceType String source, String price) {
        return new BitcoinPrice(source, price);
    }

    public static BitcoinPrice forError() {
        return new BitcoinPrice();
    }

    @SourceType
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