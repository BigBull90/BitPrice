package com.harryio.bitprice.model;

import android.support.annotation.IntDef;
import com.harryio.bitprice.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class BitcoinPrice {

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(PriceSource.COINSECURE)
    public @interface PriceSource {

        int COINSECURE = R.string.coinsecure;
        int KOINEX = R.string.koinex;
        int ZEBPAY = R.string.zebpay;
    }

    @PriceSource
    private int source;
    private String price;
    private Throwable throwable;

    private BitcoinPrice() {
    }

    private BitcoinPrice(Throwable throwable) {
        this.throwable = throwable;
    }

    private BitcoinPrice(@PriceSource int source, float price) {
        this.source = source;
        this.price = String.valueOf(price);
    }

    private BitcoinPrice(@PriceSource int source, String price) {
        this.source = source;
        this.price = price;
    }

    public static BitcoinPrice forValue(@PriceSource int source, float price) {
        return new BitcoinPrice(source, price);
    }

    public static BitcoinPrice forValue(@PriceSource int source, String price) {
        return new BitcoinPrice(source, price);
    }

    public static BitcoinPrice forError(Throwable throwable) {
        return new BitcoinPrice(throwable);
    }

    @PriceSource
    public int getSource() {
        return source;
    }

    public String getPrice() {
        return price;
    }

    public boolean isError() {
        return throwable != null;
    }

    public Throwable getError() {
        return throwable;
    }
}