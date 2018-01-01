package com.harryio.bitprice.net;

import android.support.annotation.StringDef;
import com.harryio.bitprice.model.BitcoinPrice;
import com.harryio.bitprice.model.BitcoinPrice.PriceSource;
import com.harryio.bitprice.model.Coinsecure;
import com.harryio.bitprice.model.Koinex;
import io.reactivex.Single;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ApiInteractor {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    public @interface ApiUrl {

        String COINSECURE = "https://api.coinsecure.in/v1/exchange/ticker";
        String KOINEX = "https://koinex.in/api/ticker";
        String ZEBPAY = "https://www.zebapi.com/api/v1/market/ticker/btc/inr";
    }

    public static Single<BitcoinPrice> fetchCoinsecurePrice() {
        return ServiceFactory.getService().fetchCoinsecurePrice(ApiUrl.COINSECURE)
                .map(coinSecureWrapper -> {
                    Coinsecure data = coinSecureWrapper.getData();
                    return BitcoinPrice.forValue(PriceSource.COINSECURE, data.getAsk());
                })
                .onErrorReturn(BitcoinPrice::forError);
    }

    public static Single<BitcoinPrice> fetchKoinexPrice() {
        return ServiceFactory.getService().fetchKoinexPrice(ApiUrl.KOINEX)
                .map(koinexWrapper -> {
                    Koinex data = koinexWrapper.getData();
                    return BitcoinPrice.forValue(PriceSource.KOINEX, data.getBTC());
                })
                .onErrorReturn(BitcoinPrice::forError);
    }

    public static Single<BitcoinPrice> fetchZebpayPrice() {
        return ServiceFactory.getService().fetchZebpayPrice(ApiUrl.ZEBPAY)
                .map(zebpay -> BitcoinPrice.forValue(PriceSource.ZEBPAY, zebpay.getBuy()))
                .doOnError(BitcoinPrice::forError);
    }
}
