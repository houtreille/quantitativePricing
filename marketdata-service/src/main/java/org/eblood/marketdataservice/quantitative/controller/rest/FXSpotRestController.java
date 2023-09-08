package org.eblood.marketdataservice.quantitative.controller.rest;

import com.eblood.finance.quantitative.json.v1.FXSpotDTO;
import com.eblood.finance.quantitative.marketdata.adapters.rest.handler.FxSpotApi;
import java.time.LocalDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FXSpotRestController implements FxSpotApi {


  @Override
  public ResponseEntity<FXSpotDTO> getFxSpot(String currencyPair, LocalDate dateFrom, LocalDate dateTo) {
    return FxSpotApi.super.getFxSpot(currencyPair, dateFrom, dateTo);
  }

  @Override
  public ResponseEntity<FXSpotDTO> updateFxSpot(String body) {
    return FxSpotApi.super.updateFxSpot(body);
  }
}
