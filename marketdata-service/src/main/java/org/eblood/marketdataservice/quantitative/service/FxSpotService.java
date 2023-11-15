package org.eblood.marketdataservice.quantitative.service;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.repository.FxSpotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FxSpotService {

    private final FxSpotRepository repository;

    public FxSpotService(FxSpotRepository repository) {
        this.repository = repository;
    }

    public List<FxSpot> findAll() {
        return this.repository.findAll();
    }

    public List<FxSpot> findFxSpotByDomesticCurrAndForeignCurr(String domesticCurr, String foreignCurr) {
        return this.repository.findFxSpotByDomesticCurrAndForeignCurr(domesticCurr, foreignCurr);
    }

    public FxSpot getFxSpotByDomesticCurrAndForeignCurrAndValueDate(String domesticCurr, String foreignCurr, LocalDate valueDate) {
        return this.repository.getFxSpotByDomesticCurrAndForeignCurrAndValueDate(domesticCurr, foreignCurr, valueDate);
    }

    public FxSpot save(FxSpot fxSpot) {
        return this.repository.save(fxSpot);
    }

    public List<FxSpot> saveAll(List<FxSpot> fxSpots) {
        return this.repository.saveAll(fxSpots);
    }
}
