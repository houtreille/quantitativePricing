package org.eblood.marketdataservice.quantitative.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eblood.marketdataservice.configuration.MessagingProperties;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.eblood.marketdataservice.quantitative.service.FxSpotService;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api.YahooFXSpotAPIClientException;
import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FXVolatilitytListener {

    Logger logger = org.slf4j.LoggerFactory.getLogger(FXVolatilitytListener.class);
    
    @Autowired
    MessagingProperties messagingProperties;


    @RabbitListener(queues = {"#{messagingProperties.fxExchange.volatilityQueue.name}"})
    public void listen(Message in)  {
        logger.debug(String.format("Message read from %s : %s", messagingProperties.getFxExchange().getVolatilityQueue().getName(), in));
    }

}
