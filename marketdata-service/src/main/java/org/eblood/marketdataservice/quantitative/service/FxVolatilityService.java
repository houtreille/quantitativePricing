package org.eblood.marketdataservice.quantitative.service;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.repository.FxSpotRepository;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.eblood.marketdataservice.quantitative.messaging.producer.FXSpotMessageProducer;
import org.eblood.marketdataservice.quantitative.messaging.producer.FXVolatilityMessageProducer;
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
public class FxVolatilityService {

    private final FXVolatilityMessageProducer producer;

    private final Logger log = org.slf4j.LoggerFactory.getLogger(FxVolatilityService.class);

    public FxVolatilityService(RabbitTemplate rabbitTemplate,
                               FXVolatilityMessageProducer producer) {
        this.producer = producer;

    }

    public void sendSynchronizeRequest(FXSynchronizeRequest request) {
        this.producer.sendSynchronizeRequest(request);
    }

}
