package org.eblood.quantitative.business.pricing.service;

import com.eblood.finance.quantitative.json.v1.FXOptionDTO;
import org.eblood.quantitative.business.pricing.service.option.OptionPricingService;
import org.eblood.quantitative.model.StructuredProduct;
import org.eblood.quantitative.model.option.FXOptionVanilla;
import org.eblood.quantitative.model.valuation.Valuation;
import org.eblood.quantitative.model.valuation.exception.ValuationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PricingServiceProxy {
  OptionPricingService optionPricingService;
  public PricingServiceProxy(OptionPricingService optionPricingService) {
    this.optionPricingService = optionPricingService;
  }

  public Valuation price(StructuredProduct product, LocalDate valuationDate, FXOptionDTO.PricingMethodEnum pricingMethod) throws ValuationException {

    switch(product.getProductType()) {
      case FX_OPTION_VANILLA:
        return optionPricingService.priceFXVanilla((FXOptionVanilla)product, valuationDate, pricingMethod.toString());
      default:
        throw new ValuationException(String.format("Unimplemented Product : {%s}",product.getProductType()), new Exception());
    }
  }

}
