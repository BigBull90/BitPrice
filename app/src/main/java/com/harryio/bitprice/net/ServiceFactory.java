package com.harryio.bitprice.net;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ServiceFactory {

    private static BitPriceService bitPriceService;

    private ServiceFactory() {
    }

    public static BitPriceService getService() {
        if (bitPriceService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            bitPriceService = retrofit.create(BitPriceService.class);
        }

        return bitPriceService;
    }
}