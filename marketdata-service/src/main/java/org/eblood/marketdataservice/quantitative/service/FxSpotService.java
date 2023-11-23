package org.eblood.marketdataservice.quantitative.service;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.repository.FxSpotRepository;
import org.eblood.marketdataservice.quantitative.messaging.producer.FXSpotMessageProducer;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api.FXSpotAPIClient;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api.YahooFXSpotAPIClientException;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class FxSpotService {

    private final FxSpotRepository repository;
    private final FXSpotAPIClient apiCLient;
    private final FXSpotMessageProducer producer;

    private final Logger log = org.slf4j.LoggerFactory.getLogger(FxSpotService.class);

    public FxSpotService(FxSpotRepository repository,
                         RabbitTemplate rabbitTemplate,
                         FXSpotAPIClient apiCLient,
                         FXSpotMessageProducer producer) {
        this.repository = repository;
        this.producer = producer;
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

    public FxSpot getFxSpotByLastValueDate(String currencyPair) {
        return this.repository.findFirstByDomesticCurrAndForeignCurrOrderByValueDateDesc(currencyPair.substring(0,3), currencyPair.substring(3, 6));
    }

    public FxSpot save(FxSpot fxSpot) {
        return this.repository.save(fxSpot);
    }

    public List<FxSpot> saveAll(List<FxSpot> fxSpots) {
        return this.repository.saveAll(fxSpots);
    }

    public List<FxSpot> getFxSpotHistory(String currencyPair) throws YahooFXSpotAPIClientException {
        return this.apiCLient.retrieveHistory(currencyPair);
    }


    public List<FxSpot> synchronizeFxSpotHistory(String currencyPair) throws YahooFXSpotAPIClientException {

        final LocalDate lastValueDate = Optional.ofNullable(this.getFxSpotByLastValueDate(currencyPair))
                .map(FxSpot::getValueDate)
                .orElse(null);

        List<FxSpot> dbFxSpots = getFxSpotHistory(currencyPair);

        List<FxSpot> savedFxSpots = saveAll(dbFxSpots.stream().filter(
                fxSpot -> lastValueDate == null || fxSpot.getValueDate().isAfter(lastValueDate))
                .collect(Collectors.toList()));

        log.debug(savedFxSpots.size() + " fx spots saved");


        return savedFxSpots;
    }

    public void sendSynchronizeRequest(FXSynchronizeRequest request) {
        this.producer.sendSynchronizeRequest(request);
    }

    @Scheduled(cron = "0 15 15 * * *")
    public void scheduledTask() throws YahooFXSpotAPIClientException {
       getFxSpotHistory(null);
    }
}
