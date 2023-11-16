package org.eblood.marketdataservice.quantitative.service;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.repository.FxSpotRepository;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api.FXSpotAPIClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class FxSpotService {

    private final FxSpotRepository repository;
    private final FXSpotAPIClient apiCLient;
    private final RabbitTemplate rabbitTemplate;

    public FxSpotService(FxSpotRepository repository,
                         RabbitTemplate rabbitTemplate,
                         FXSpotAPIClient apiCLient) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
        this.apiCLient = apiCLient;
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

    public List<FxSpot> getFxSpotHistory() throws IOException, InterruptedException {
        return this.apiCLient.retrieveHistory();
    }

    public void sendSynchronizeRequest() {
        rabbitTemplate.convertAndSend("myQueue", "synchronize");
       // rabbitTemplate.convertAndSend("myQueue", "Hello, world!");
    }
}
