package org.eblood.quantitative.business.pricing;

import org.eblood.quantitative.model.ProductEnum;
import org.eblood.quantitative.model.StructuredProduct;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceProxy {

  public void price(StructuredProduct productType) {

    switch(productType.getProductType().toString()) {
      case "FX_OPTION_VANILLA":

      default:
    }
  }

}
