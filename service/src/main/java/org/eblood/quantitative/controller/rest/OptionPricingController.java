package org.eblood.quantitative.controller.rest;


import com.eblood.finance.quantitative.json.v1.FXOptionDTO;
import com.eblood.finance.quantitative.option.adapters.rest.handler.OptionApi;
import org.eblood.quantitative.business.pricing.service.PricingServiceProxy;
import org.eblood.quantitative.mapper.JsonMapper;
import org.eblood.quantitative.model.option.FXOptionVanilla;
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


  public ResponseEntity<String> optionFxVanillaPost(FXOptionDTO body) {

     FXOptionVanilla optionToPrice = mapper.map(body);

     pricingService.price(optionToPrice, body.);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


}
