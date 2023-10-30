package org.eblood.quantitative.controller.rest;

import com.eblood.finance.quantitative.option.adapters.rest.handler.TestApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements TestApi {

    @Override
    public ResponseEntity<Void> getTest() {
        //fxSpotApi.getFxSpot("EURUSD",null,null);
        return null;
    }
}
