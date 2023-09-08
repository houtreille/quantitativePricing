package org.eblood.quantitative.controller.rest;


import com.eblood.finance.quantitative.json.v1.FXOptionDTO;
import com.eblood.finance.quantitative.json.v1.ValuationDTO;
import com.eblood.finance.quantitative.option.adapters.rest.handler.OptionApi;
import org.eblood.quantitative.business.pricing.service.PricingServiceProxy;
import org.eblood.quantitative.mapper.JsonMapper;
import org.eblood.quantitative.model.option.FXOptionVanilla;
import org.eblood.quantitative.model.valuation.Valuation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionPricingController implements OptionApi {


  PricingServiceProxy pricingService;
  JsonMapper mapper;
  public OptionPricingController(PricingServiceProxy pricingService,
      JsonMapper mapper) {
    this.pricingService = pricingService;
    this.mapper = mapper;
  }


  public ResponseEntity<ValuationDTO> vanillaFxOptionPricing(FXOptionDTO body) {
     FXOptionVanilla optionToPrice = mapper.map(body);
     Valuation optionValuation = pricingService.price(optionToPrice, body.getValuationDate(), body.getPricingMethod());

     return new ResponseEntity<>(mapper.map(optionValuation), HttpStatus.OK);
  }


}
