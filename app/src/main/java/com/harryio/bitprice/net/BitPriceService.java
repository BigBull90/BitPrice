package com.harryio.bitprice.net;

import com.harryio.bitprice.model.CoinDelta;
import com.harryio.bitprice.model.CoinSecureWrapper;
import com.harryio.bitprice.model.KoinexWrapper;
import com.harryio.bitprice.model.LocalBitcoins;
import com.harryio.bitprice.model.Paxful;
import com.harryio.bitprice.model.RateWrapper;
import com.harryio.bitprice.model.Zebpay;
import io.reactivex.Single;
import java.util.List;
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

    @GET
    Single<Paxful> fetchPaxfulPrice(@Url String url);

    @GET
    Single<LocalBitcoins> fetchLocalBitcoinPrice(@Url String url);

    @GET
    Single<List<CoinDelta>> fetchCoinDeltaPrice(@Url String url);
}
