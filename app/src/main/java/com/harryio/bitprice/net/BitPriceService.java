package com.harryio.bitprice.net;

import com.harryio.bitprice.model.CoinSecureWrapper;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BitPriceService {

    @GET
    Single<CoinSecureWrapper> fetchCoinsecurePrice(@Url String url);
}
