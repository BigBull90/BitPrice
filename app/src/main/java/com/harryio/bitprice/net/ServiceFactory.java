package com.harryio.bitprice.net;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

final class ServiceFactory {

    private static BitPriceService bitPriceService;

    private ServiceFactory() {
    }

    static BitPriceService getService() {
        if (bitPriceService == null) {
            OkHttpClient okHttpClient = new OkHttpClient();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://myapi.com/")
                    .client(okHttpClient)
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            bitPriceService = retrofit.create(BitPriceService.class);
        }

        return bitPriceService;
    }
}