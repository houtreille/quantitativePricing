package org.eblood.marketdataservice.quantitative.messaging.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.eblood.marketdataservice.configuration.MessagingProperties;
import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.eblood.marketdataservice.quantitative.service.FxSpotService;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api.FXSpotAPIClient;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api.YahooFXSpotAPIClientException;
import org.slf4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FXSpotListener {

    Logger logger = org.slf4j.LoggerFactory.getLogger(FXSpotListener.class);

    @Autowired
    FxSpotService fxSpotService;

    @Autowired
    MessagingProperties messagingProperties;
    @RabbitListener(queues = {"#{messagingProperties.fxExchange.spotQueue.name}"})
    public void listen(Message in)  {

        String contentType = in.getMessageProperties().getContentType();

        if(contentType.equals(MessageProperties.CONTENT_TYPE_JSON)) {
            try {
                FXSynchronizeRequest fxSynchronizeRequest = new ObjectMapper().readValue(in.getBody(), FXSynchronizeRequest.class);
                fxSpotService.synchronizeFxSpotHistory(fxSynchronizeRequest.getCurrencyPair());
            } catch (YahooFXSpotAPIClientException e) {
                //TODO : send to deadletter
                //throw new RuntimeException(e);
            } catch (IOException e) {
                //throw new RuntimeException(e);
                //TODO : send to deadletter
            }

            if(logger.isDebugEnabled()) {
                logger.debug(String.format("Message read from %s : %s", messagingProperties.getFxExchange().getSpotQueue().getName(), in));
            }
        } else {
            logger.error("Message is not JSON");
        }
    }

}
