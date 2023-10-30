package org.eblood.quantitative.business.pricing.service.option;

import java.time.LocalDate;

import com.eblood.finance.quantitative.marketdata.v1.FXSpotDTO;
import lombok.var;
import org.eblood.quantitative.controller.rest.client.MarketdataProvider;
import org.eblood.quantitative.model.option.FXOptionVanilla;
import org.eblood.quantitative.model.valuation.Valuation;
import org.springframework.stereotype.Service;

@Service
public class OptionPricingService {

  private final MarketdataProvider marketdataProvider;

  public OptionPricingService(MarketdataProvider marketdataProvider) {
    this.marketdataProvider = marketdataProvider;
  }


  public Valuation priceFXVanilla(FXOptionVanilla option, LocalDate valuationDate, String method) {

    FXSpotDTO fxSpotDTO = marketdataProvider.getFxSpot(option.getCurr1()+option.getCurr2(), valuationDate, valuationDate);
    var value = fxSpotDTO.getValue();

    Valuation optionValuation = Valuation.builder()
            .valuationDate(valuationDate)
            .currency(option.getCurr1())
            .value(value)
            .build();

    return optionValuation;
  }
}
