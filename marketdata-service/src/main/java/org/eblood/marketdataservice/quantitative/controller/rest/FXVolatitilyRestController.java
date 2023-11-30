package org.eblood.marketdataservice.quantitative.controller.rest;

import com.eblood.finance.quantitative.json.v1.*;
import com.eblood.finance.quantitative.marketdata.adapters.rest.handler.FxSpotApi;
import com.eblood.finance.quantitative.marketdata.adapters.rest.handler.FxVolatilityApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.eblood.marketdataservice.quantitative.domain.model.repository.FxSpotRepository;
import org.eblood.marketdataservice.quantitative.mapper.json.JsonMapper;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.eblood.marketdataservice.quantitative.service.FxSpotService;
import org.eblood.marketdataservice.quantitative.service.FxVolatilityService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FXVolatitilyRestController implements FxVolatilityApi {


    private final FxVolatilityService service;
    private final JsonMapper jsonMapper;


    public FXVolatitilyRestController(FxVolatilityService service,
                                      JsonMapper jsonMapper) {
        this.service = service;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public ResponseEntity<List<FXVolatilityDTO>> searchFxVolatility(FXVolatilitySearchDTO body) {
        List<FXVolatilityDTO> dtos = service.findAll()
                .stream()
                .map(jsonMapper::mapVolatility)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }

    @Override
    public ResponseEntity<List<FXVolatilityDTO>> synchronizeFxVolatility(FXVolatilitySynchronizeDTO body, String provider, Boolean publish) {

        service.sendSynchronizeRequest(FXSynchronizeRequest.builder()
                    .currencyPair(body.getCurr1() + body.getCurr2())
                    .type(body.getDate() == null ? FXSynchronizeRequest.FULL_HISTORY : FXSynchronizeRequest.MISSING_HISTORY)
                    .provider(body.getProvider())
                    .build());

            return ResponseEntity.ok(null);
    }


}
