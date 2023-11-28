package org.eblood.marketdataservice.quantitative.messaging.producer;

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
public class FXVolatilityMessageProducer extends FXMessageProducer {

    public FXVolatilityMessageProducer(RabbitTemplate rabbitTemplate, MessagingProperties messagingProperties) {
        super(rabbitTemplate, messagingProperties);
    }

    @Override
    protected Object getDataSet() {
        return Utils.FX_VOLATILITY_DATASET;
    }
}
