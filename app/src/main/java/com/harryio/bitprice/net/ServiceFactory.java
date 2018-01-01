package com.harryio.bitprice.net;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

final class ServiceFactory {

    private static BitPriceService bitPriceService;

    private ServiceFactory() {
    }

    static BitPriceService getService() {
        if (bitPriceService == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
            
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