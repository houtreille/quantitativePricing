package org.eblood.marketdataservice.quantitative.controller.rest;

import com.eblood.finance.quantitative.json.v1.FXSpotDTO;
import com.eblood.finance.quantitative.marketdata.adapters.rest.handler.FxSpotApi;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FXSpotRestController implements FxSpotApi {


  @Override
  public ResponseEntity<FXSpotDTO> getFxSpot(String currencyPair,
         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

    FXSpotDTO fxSpotDTO = new FXSpotDTO()
            .curr1("EUR")
            .curr2("USD")
            .date(dateFrom)
            .value(new Random().nextDouble());

    return ResponseEntity.ok(fxSpotDTO);

    //return FxSpotApi.super.getFxSpot(currencyPair, dateFrom, dateTo);
  }

  @Override
  public ResponseEntity<FXSpotDTO> updateFxSpot(String body) {
    return FxSpotApi.super.updateFxSpot(body);
  }
}
