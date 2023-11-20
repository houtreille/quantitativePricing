package org.eblood.marketdataservice.quantitative.messaging;

import ch.qos.logback.classic.pattern.MessageConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FXSpotMessageProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ObjectMapper objectMapper;

    public void sendSynchronizeRequest(FXSynchronizeRequest request) {


        var messageProperties = new MessageProperties();
        messageProperties.setHeader("contentType", MessageProperties.CONTENT_TYPE_JSON);
        //This contentType is required to make the MessageConverter use the adapted converter
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        //CONVERT request TO JSON
        var converter = rabbitTemplate.getMessageConverter();


        ObjectMapper m = new ObjectMapper();
        try {
           Object a =  m.writeValueAsString(request);
            System.out.println(a);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        var message = converter.toMessage(request, messageProperties);

        rabbitTemplate.send("myQueue", message);
    }

}
