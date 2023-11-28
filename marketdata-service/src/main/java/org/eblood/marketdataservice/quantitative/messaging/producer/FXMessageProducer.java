package org.eblood.marketdataservice.quantitative.messaging.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eblood.marketdataservice.configuration.MessagingProperties;
import org.eblood.marketdataservice.quantitative.messaging.Utils;
import org.eblood.marketdataservice.quantitative.messaging.model.FXSynchronizeRequest;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import static org.eblood.marketdataservice.configuration.MessagingConfiguration.DATASET_HEADER;
import static org.eblood.marketdataservice.configuration.MessagingConfiguration.EMPTY_ROUTING_KEY;
import static org.springframework.amqp.core.MessageProperties.CONTENT_TYPE_JSON;


public abstract class FXMessageProducer {

    RabbitTemplate rabbitTemplate;
    MessagingProperties messagingProperties;

    private static String ROUTING_KEY_USER_IMPORTANT_WARN = "user.important.warn";
    private static String ROUTING_KEY_USER_IMPORTANT_ERROR = "user.important.error";

    public FXMessageProducer(RabbitTemplate rabbitTemplate, MessagingProperties messagingProperties) {
        this.rabbitTemplate = rabbitTemplate;
        this.messagingProperties = messagingProperties;
    }

    public void sendSynchronizeRequest(FXSynchronizeRequest request) {
        var messageProperties = getMessageProperties(request);

        var converter = rabbitTemplate.getMessageConverter();
        var message = converter.toMessage(request, messageProperties);


        rabbitTemplate.convertAndSend(messagingProperties.getTopicExchange().getName(), ROUTING_KEY_USER_IMPORTANT_WARN, "topic important warn" + message);
        rabbitTemplate.convertAndSend(messagingProperties.getTopicExchange().getName(), ROUTING_KEY_USER_IMPORTANT_ERROR,  "topic important error" + message);

        rabbitTemplate.send(messagingProperties.getFxExchange().getName(), EMPTY_ROUTING_KEY, message);
    }

    @RabbitListener(queues = {"#{messagingProperties.topicExchange.testQueue1.name}"})
    public void receiveMessageFromFanout1(String message) {
        System.out.println("Received fanout 1 message: " + message);
    }

    @RabbitListener(queues = {"#{messagingProperties.topicExchange.testQueue2.name}"})
    public void receiveMessageFromFanout2(String message) {
        System.out.println("Received fanout 2 message: " + message);
    }

    private MessageProperties getMessageProperties(FXSynchronizeRequest request) {
        var messageProperties = new MessageProperties();
        messageProperties.setHeader("contentType", CONTENT_TYPE_JSON);
        messageProperties.setHeader(DATASET_HEADER, getDataSet());
        //This contentType is required to make the MessageConverter use the adapted converter
        messageProperties.setContentType(CONTENT_TYPE_JSON);

        return messageProperties;
    }

    protected abstract Object getDataSet();


}
