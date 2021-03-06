package com.harryio.bitprice.net;

import com.harryio.bitprice.model.CoinDelta;
import com.harryio.bitprice.model.CoinSecure;
import com.harryio.bitprice.model.Koinex;
import com.harryio.bitprice.model.LocalBitcoins;
import com.harryio.bitprice.model.Paxful;
import com.harryio.bitprice.model.Rate;
import com.harryio.bitprice.model.Zebpay;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BitPriceService {

    @GET
    Single<CoinSecure> fetchCoinsecurePrice(@Url String url);

    @GET
    Single<Koinex> fetchKoinexPrice(@Url String url);

    @GET
    Single<Zebpay> fetchZebpayPrice(@Url String url);

    @GET
    Single<Rate> fetchDollarRate(@Url String url);

    @GET
    Single<Paxful> fetchPaxfulPrice(@Url String url);

    @GET
    Single<LocalBitcoins> fetchLocalBitcoinPrice(@Url String url);

    @GET
    Single<List<CoinDelta>> fetchCoinDeltaPrice(@Url String url);
}
