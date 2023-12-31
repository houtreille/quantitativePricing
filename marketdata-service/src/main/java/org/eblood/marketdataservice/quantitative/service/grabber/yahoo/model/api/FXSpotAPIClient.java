package org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.FXSpotResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FXSpotAPIClient {

    public static String YAHOO_API = "YAHOO";

    public List<FxSpot> retrieveHistory(String currencyPair) throws YahooFXSpotAPIClientException {

        List<FxSpot> fxSpots = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format("https://yahoo-finance15.p.rapidapi.com/api/v1/markets/stock/history?symbol=%s&interval=%s&diffandsplits=false", currencyPair+"=X", "1d")))
                .header("X-RapidAPI-Key", "eaa590df7emshb8a4a902a3aa259p1f009fjsn94c3393c2d99")
                .header("X-RapidAPI-Host", "yahoo-finance15.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new YahooFXSpotAPIClientException(e);
        }

        if(response.statusCode() != 404) {
            try {
                FXSpotResponse fxSpotResponse = mapper.readValue(response.body(), FXSpotResponse.class);

                fxSpotResponse.getBody().keySet().forEach(key -> {

                    FXSpotResponse.Body daySpot = fxSpotResponse.getBody().get(key);

                    fxSpots.add(FxSpot.builder()
                            .domesticCurr(fxSpotResponse.getMeta().getSymbol().substring(0, 3))
                            .foreignCurr(fxSpotResponse.getMeta().getSymbol().substring(3, 6))
                            .low(Optional.of(daySpot.getLow().doubleValue()).orElse(-1.))
                            .high(Optional.of(fxSpotResponse.getBody().get(key).getHigh().doubleValue()).orElse(-1.))
                            .volume(Optional.of(fxSpotResponse.getBody().get(key).getVolume().doubleValue()).orElse(-1.))
                            .valueDate(LocalDate.parse(fxSpotResponse.getBody().get(key).getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                            .value(fxSpotResponse.getBody().get(key).getClose().doubleValue())
                            .exchangeName(fxSpotResponse.getMeta().getExchangeName()+"_"+YAHOO_API)
                            .build());
                });
            } catch(Exception e) {
                throw new YahooFXSpotAPIClientException("Error occurs during API call ", e);
            }
        } else {
            throw new YahooFXSpotAPIClientException("API request returns an invalid response ", null);
        }

        return fxSpots;
    }


}
