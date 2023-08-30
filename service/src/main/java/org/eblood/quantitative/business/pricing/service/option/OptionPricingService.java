package org.eblood.quantitative.business.pricing.service.option;

import java.time.LocalDate;
import org.eblood.quantitative.model.StructuredProduct;
import org.eblood.quantitative.model.option.FXOptionVanilla;
import org.eblood.quantitative.model.valuation.Valuation;
import org.springframework.stereotype.Service;

@Service
public class OptionPricingService {

  public Valuation priceFXVanilla(StructuredProduct option, LocalDate valuationDate, String method) {

    double value = ((FXOptionVanilla)option).getStrike();

    Valuation optionValuation = Valuation.builder().valuationDate(valuationDate)
            .currency(((FXOptionVanilla) option).getCurr1())
            .value(value)
            .build();

    return optionValuation;
  }
}
