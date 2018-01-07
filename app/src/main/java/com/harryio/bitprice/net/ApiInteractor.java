package com.harryio.bitprice.net;

import android.support.annotation.StringDef;
import com.harryio.bitprice.model.BitcoinPrice;
import com.harryio.bitprice.model.BitcoinPrice.PriceSource;
import com.harryio.bitprice.model.Rate;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ApiInteractor {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    public @interface ApiUrl {

        String DOLLAR_RATE = "https://api.fixer.io/latest?base=USD&symbols=INR";
        String COINSECURE = "https://api.coinsecure.in/v1/exchange/ticker";
        String KOINEX = "https://koinex.in/api/ticker";
        String ZEBPAY = "https://www.zebapi.com/api/v1/market/ticker/btc/inr";
        String PAXFUL = "https://paxful.com/api/currency/btc";
        String LOCAL_BITCOINS = "https://localbitcoins.com/bitcoinaverage/ticker-all-currencies/";
        String COINDELTA = "https://coindelta.com/api/v1/public/getticker/";
    }

    public static Single<BitcoinPrice> fetchCoinsecurePrice() {
        return ServiceFactory.getService().fetchCoinsecurePrice(ApiUrl.COINSECURE)
                .map(coinSecure -> BitcoinPrice
                        .forValue(PriceSource.COINSECURE, coinSecure.getPrice()))
                .onErrorReturn(BitcoinPrice::forError);
    }

    public static Single<BitcoinPrice> fetchKoinexPrice() {
        return ServiceFactory.getService().fetchKoinexPrice(ApiUrl.KOINEX)
                .map(koinex -> BitcoinPrice.forValue(PriceSource.KOINEX, koinex.getPrice()))
                .onErrorReturn(BitcoinPrice::forError);
    }

    public static Single<BitcoinPrice> fetchZebpayPrice() {
        return ServiceFactory.getService().fetchZebpayPrice(ApiUrl.ZEBPAY)
                .map(zebpay -> BitcoinPrice.forValue(PriceSource.ZEBPAY, zebpay.getBuy()))
                .doOnError(BitcoinPrice::forError);
    }

    public static Single<BitcoinPrice> fetchPaxfulPrice() {
        return ServiceFactory.getService().fetchPaxfulPrice(ApiUrl.PAXFUL)
                .zipWith(ServiceFactory.getService().fetchDollarRate(ApiUrl.DOLLAR_RATE),
                        (paxful, rateWrapper) -> {
                            Rate dollarRate = rateWrapper.getRate();
                            float bitcoinPriceInDollar = paxful.getPrice();
                            float inrPrice = bitcoinPriceInDollar * dollarRate.getInr();
                            return BitcoinPrice.forValue(PriceSource.PAXFUL, inrPrice);
                        })
                .doOnError(BitcoinPrice::forError);
    }

    public static Single<BitcoinPrice> fetchLocalBitcoinPrice() {
        return ServiceFactory.getService().fetchLocalBitcoinPrice(ApiUrl.LOCAL_BITCOINS)
                .map(localBitcoins -> BitcoinPrice
                        .forValue(PriceSource.LOCAL_BITCOINS, localBitcoins.getPrice()))
                .doOnError(BitcoinPrice::forError);
    }

    public static Single<BitcoinPrice> fetchCoinDeltaPrice() {
        return ServiceFactory.getService().fetchCoinDeltaPrice(ApiUrl.COINDELTA).toObservable()
                .flatMap(Observable::fromIterable)
                .filter(coinDelta -> coinDelta.getMarketName().equals("btc-inr"))
                .map(coinDelta -> BitcoinPrice
                        .forValue(PriceSource.COINDELTA, coinDelta.getPrice()))
                .singleOrError()
                .doOnError(BitcoinPrice::forError);
    }
}
