package com.harryio.bitprice.net;

import android.support.annotation.StringDef;
import com.harryio.bitprice.model.BitcoinPrice;
import com.harryio.bitprice.model.BitcoinPrice.PriceSource;
import com.harryio.bitprice.model.CoinSecureWrapper;
import com.harryio.bitprice.model.Coinsecure;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ApiInteractor {

    @Retention(RetentionPolicy.SOURCE)
    @StringDef
    public @interface ApiUrl {

        String COINSECURE = "https://api.coinsecure.in/v1/exchange/ticker";
    }

    public static Single<BitcoinPrice> fetchCoinsecurePrice() {
        return ServiceFactory.getService().fetchCoinsecurePrice(ApiUrl.COINSECURE)
                .map(new Function<CoinSecureWrapper, BitcoinPrice>() {
                    @Override
                    public BitcoinPrice apply(CoinSecureWrapper coinSecureWrapper)
                            throws Exception {
                        Coinsecure data = coinSecureWrapper.getData();
                        return BitcoinPrice.forValue(PriceSource.COINSECURE, data.getAsk());
                    }
                })
                .onErrorReturn(new Function<Throwable, BitcoinPrice>() {
                    @Override
                    public BitcoinPrice apply(Throwable throwable) throws Exception {
                        return BitcoinPrice.forError(throwable);
                    }
                });
    }
}
