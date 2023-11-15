package org.eblood.marketdataservice.quantitative.controller.rest;

import com.eblood.finance.quantitative.json.v1.FXSportSearchDTO;
import com.eblood.finance.quantitative.json.v1.FXSpotDTO;
import com.eblood.finance.quantitative.marketdata.adapters.rest.handler.FxSpotApi;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.repository.FxSpotRepository;
import org.eblood.marketdataservice.quantitative.mapper.json.JsonMapper;
import org.eblood.marketdataservice.quantitative.service.FxSpotService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FXSpotRestController implements FxSpotApi {


  private final FxSpotService service;
  private final JsonMapper jsonMapper;
    private final FxSpotRepository fxSpotRepository;

    public FXSpotRestController(FxSpotService service, JsonMapper jsonMapper,
                                FxSpotRepository fxSpotRepository) {
    this.service = service;
    this.jsonMapper = jsonMapper;
        this.fxSpotRepository = fxSpotRepository;
    }


    @Override
  public ResponseEntity<FXSpotDTO> getFxSpot(String currencyPair,
         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

    FXSpotDTO fxSpotDTO = new FXSpotDTO()
            .curr1("EUR")
            .curr2("USD")
            .date(dateFrom)
            .value(new Random().nextDouble());

    return ResponseEntity.ok(fxSpotDTO);

    //return FxSpotApi.super.getFxSpot(currencyPair, dateFrom, dateTo);
  }

    @Override
    public ResponseEntity<List<FXSpotDTO>> searchFxSpots(FXSportSearchDTO body) {

        if(body.getDate() == null) {
            return service.findFxSpotByDomesticCurrAndForeignCurr(body.getCurr1(), body.getCurr2())
                    .stream()
                    .map(fxSpot -> jsonMapper.map(fxSpot))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), ResponseEntity::ok));
        } else {
            return ResponseEntity.ok(Collections.singletonList(jsonMapper.map(service.getFxSpotByDomesticCurrAndForeignCurrAndValueDate(body.getCurr1(), body.getCurr2(), body.getDate()))));
        }
    }


    @Override
    public ResponseEntity<List<FXSpotDTO>> getAllFxSpots() {
         List<FXSpotDTO> fxSpotDTOS = service.findAll().stream()
                 .map(fxSpot -> jsonMapper.map(fxSpot))
                 .collect(Collectors.toList());

         return ResponseEntity.ok(fxSpotDTOS);
    }


    @Override
  public ResponseEntity<List<FXSpotDTO>> updateFxSpot(List<FXSpotDTO> dtos) {

        // service.saveAll(dtos.stream().map(fxSpotDTO -> jsonMapper.map(fxSpotDTO)).collect(Collectors.toList()));

    List<FxSpot> spots = dtos.stream().map(fxSpotDTO -> jsonMapper.map(fxSpotDTO)).collect(Collectors.toList());
        /**
         * write me a for loop browsing the list of spots
         * for each spot, check if it exists in the database
         * if it exists, update it
         * if it does not exist, create it
         */

        for (FxSpot spot : spots) {
            FxSpot existingSpot = fxSpotRepository.getFxSpotByDomesticCurrAndForeignCurrAndValueDate(spot.getDomesticCurr(),
                    spot.getForeignCurr(), spot.getValueDate());
            if (existingSpot != null) {
                existingSpot.setValue(spot.getValue());
                fxSpotRepository.save(existingSpot);
            } else {
                fxSpotRepository.save(spot);
            }
        }


     List<FXSpotDTO> spotDtos = service.findAll().stream().map(jsonMapper::map).collect(Collectors.toList());

     return ResponseEntity.ok(spotDtos);
  }
}
