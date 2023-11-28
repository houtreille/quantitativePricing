package org.eblood.marketdataservice.quantitative.controller.rest;

import com.eblood.finance.quantitative.json.v1.FXSpotDTO;
import com.eblood.finance.quantitative.json.v1.FXVolatilityDTO;
import com.eblood.finance.quantitative.json.v1.FXVolatilitySynchronizeDTO;
import com.eblood.finance.quantitative.json.v1.ProblemDTO;
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

@RestController
public class FXVolatitilyRestController {


    private final FxVolatilityService service;
    private final JsonMapper jsonMapper;


    public FXVolatitilyRestController(FxVolatilityService service,
                                      JsonMapper jsonMapper) {
        this.service = service;
        this.jsonMapper = jsonMapper;
    }


    @Operation(summary = "Synchronize volatility spots from XXX for a given criteria", description = "", tags={ "fxVolatility" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Grabbing fxVolatility from XXX", content = @Content(mediaType = "application/vnd.ams.fxVolatility-search-api.v2+json", array = @ArraySchema(schema = @Schema(implementation = FXSpotDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid status value", content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDTO.class))),
            @ApiResponse(responseCode = "200", description = "unexpected error", content = @Content(mediaType = "application/problem+json", schema = @Schema(implementation = ProblemDTO.class))) }
    )

    @RequestMapping(value = "/fxVolatility/synchronize",
            produces = { "application/vnd.ams.fxVolatility-search-api.v2+json", "application/problem+json" },
            consumes = { "application/vnd.ams.fxVolatility-synchronize-api+json" },
            method = RequestMethod.POST)

    ResponseEntity<List<FXVolatilityDTO>> synchronizeFxVolatility(
            //@Parameter(in = ParameterIn.DEFAULT, description = "", required=true, schema=@Schema())
            @RequestBody FXVolatilitySynchronizeDTO body,
            //@Parameter(in = ParameterIn.QUERY, description = "where to search for fxVolatility" ,schema=@Schema(allowableValues={ "BBG", "Binance" }))
            @RequestParam(required = false) String provider,
            @RequestParam(value = "publish", required = true) Boolean publish) {

        service.sendSynchronizeRequest(FXSynchronizeRequest.builder()
                        .currencyPair(body.getCurr1() + body.getCurr2())
                        .type(body.getDate() == null ? FXSynchronizeRequest.FULL_HISTORY : FXSynchronizeRequest.MISSING_HISTORY)
                        .provider(body.getProvider())
                        .build());

        return ResponseEntity.ok(null);
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
