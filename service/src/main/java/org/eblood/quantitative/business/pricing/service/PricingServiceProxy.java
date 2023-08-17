package org.eblood.quantitative.business.pricing.service;

import org.eblood.quantitative.business.pricing.service.option.OptionPricingService;
import org.eblood.quantitative.model.ProductEnum;
import org.eblood.quantitative.model.StructuredProduct;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceProxy {
  OptionPricingService optionPricingService;
  public PricingServiceProxy(OptionPricingService optionPricingService) {
    this.optionPricingService = optionPricingService;
  }

  public void price(StructuredProduct product) {

    switch(product.getProductType().toString()) {
      case "FX_OPTION_VANILLA":
        optionPricingService.priceFXVanilla(product, )
      default:
    }
  }

}
