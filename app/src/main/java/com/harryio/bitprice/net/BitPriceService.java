package com.harryio.bitprice.net;

import com.harryio.bitprice.model.CoinSecureWrapper;
import com.harryio.bitprice.model.KoinexWrapper;
import com.harryio.bitprice.model.RateWrapper;
import com.harryio.bitprice.model.Zebpay;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BitPriceService {

    @GET
    Single<CoinSecureWrapper> fetchCoinsecurePrice(@Url String url);

    @GET
    Single<KoinexWrapper> fetchKoinexPrice(@Url String url);

    @GET
    Single<Zebpay> fetchZebpayPrice(@Url String url);

    @GET
    Single<RateWrapper> fetchDollarRate(@Url String url);
}
