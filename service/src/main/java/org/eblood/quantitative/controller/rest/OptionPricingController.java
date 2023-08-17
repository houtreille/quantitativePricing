package org.eblood.quantitative.controller.rest;


import com.eblood.finance.quantitative.json.v1.FXOptionDTO;
import com.eblood.finance.quantitative.option.adapters.rest.handler.OptionApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OptionPricingController implements OptionApi {


  public ResponseEntity<String> optionFxVanillaPost(FXOptionDTO body) {
    return OptionApi.super.optionFxVanillaPost(body);
  }


}
