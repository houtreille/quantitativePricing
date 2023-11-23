package org.eblood.marketdataservice.quantitative.messaging.producer;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eblood.marketdataservice.configuration.MessagingProperties;
import org.eblood.marketdataservice.quantitative.messaging.Utils;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.eblood.marketdataservice.configuration.MessagingConfiguration.DATASET_HEADER;
import static org.eblood.marketdataservice.configuration.MessagingConfiguration.EMPTY_ROUTING_KEY;
import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;

@Service
public class FXSpotMessageProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MessagingProperties messagingProperties;

    public void sendSynchronizeRequest(FXSynchronizeRequest request) {
        var messageProperties = getMessageProperties(request);

        var converter = rabbitTemplate.getMessageConverter();
        var message = converter.toMessage(request, messageProperties);

        rabbitTemplate.send(messagingProperties.getFxSpotHistoryRequestExchange().getName(), EMPTY_ROUTING_KEY, message);
    }


    private MessageProperties getMessageProperties(FXSynchronizeRequest request) {
        var messageProperties = new MessageProperties();
        messageProperties.setHeader("contentType", CONTENT_TYPE_JSON);
        messageProperties.setHeader(DATASET_HEADER, Utils.FX_SPOT_DATASET);
        //This contentType is required to make the MessageConverter use the adapted converter
        messageProperties.setContentType(CONTENT_TYPE_JSON);

        return messageProperties;
    }
}
