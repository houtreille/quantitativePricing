package org.eblood.marketdataservice.quantitative.controller.rest;

import com.eblood.finance.quantitative.json.v1.FXSpotDTO;
import com.eblood.finance.quantitative.json.v1.FXSpotSearchDTO;
import com.eblood.finance.quantitative.json.v1.FXSpotSynchronizeDTO;
import com.eblood.finance.quantitative.json.v1.ProblemDTO;
import com.eblood.finance.quantitative.marketdata.adapters.rest.handler.FxSpotApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.repository.FxSpotRepository;
import org.eblood.marketdataservice.quantitative.mapper.json.JsonMapper;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.eblood.marketdataservice.quantitative.service.FxSpotService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
public class FXVolatitilyRestController {


    private final FxSpotService service;
    private final JsonMapper jsonMapper;
    private final FxSpotRepository fxSpotRepository;
    private final RabbitTemplate rabbitTemplate;

    public FXVolatitilyRestController(FxSpotService service,
                                      JsonMapper jsonMapper,
                                      FxSpotRepository fxSpotRepository,
                                      RabbitTemplate rabbitTemplate) {
        this.service = service;
        this.jsonMapper = jsonMapper;
        this.fxSpotRepository = fxSpotRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

 /*   @Operation(summary = "Synchronize volatility spots from XXX for a given criteria", description = "", tags={ "fxVolatility" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grabbing fxVolatility from XXX", content = @Content(mediaType = "application/vnd.ams.fxVolatility-search-api.v2+json", array = @ArraySchema(schema = @Schema(implementation = FXSpotDTO.class)))),

            @ApiResponse(responseCode = "400", description = "Invalid status value", content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDTO.class))),

            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDTO.class))) })
    @RequestMapping(value = "/fxVolatility/synchronize",
            produces = { "application/vnd.ams.fxVolatility-search-api.v2+json", "application/problem+json" },
            consumes = { "application/vnd.ams.fxVolatility-synchronize-api+json" },
            method = RequestMethod.POST)
    public ResponseEntity<List<FXSpotDTO>> synchronizeFxVol(FXSpotSynchronizeDTO body, String provider, Boolean publish) {

        FXSynchronizeRequest request = FXSynchronizeRequest.builder()
                        .currencyPair(body.getCurr1() + body.getCurr2())
                        .type(body.getDate() == null ? FXSynchronizeRequest.FULL_HISTORY : FXSynchronizeRequest.MISSING_HISTORY)
                        .provider(FXSynchronizeRequest.YAHOO_PROVIDER)
                        .build();

        service.sendSynchronizeRequest(request);
        return null;
    }*/

}
