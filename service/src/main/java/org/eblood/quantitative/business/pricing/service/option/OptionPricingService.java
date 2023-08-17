package org.eblood.quantitative.business.pricing.service.option;

import java.time.LocalDate;
import org.eblood.quantitative.model.StructuredProduct;
import org.eblood.quantitative.model.option.FXOptionVanilla;
import org.springframework.stereotype.Service;

@Service
public class OptionPricingService {

  public Double priceFXVanilla(StructuredProduct option, LocalDate valuationDate, String method) {
    return ((FXOptionVanilla)option).getStrike();
  }


}
