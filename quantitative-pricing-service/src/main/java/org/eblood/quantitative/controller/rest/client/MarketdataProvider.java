package org.eblood.quantitative.controller.rest.client;

import com.eblood.finance.quantitative.marketdata.client.FxSpotApi;
import com.eblood.finance.quantitative.marketdata.v1.FXSpotDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MarketdataProvider {

    private final FxSpotApi fxSpotApi;

    public MarketdataProvider(FxSpotApi fxSpotApi) {
        this.fxSpotApi = fxSpotApi;
    }

    public FXSpotDTO getFxSpot(String currencyPair, LocalDate dateFrom, LocalDate dateTo) {
        return fxSpotApi.getFxSpot(currencyPair, dateFrom, dateTo);
    }
}
