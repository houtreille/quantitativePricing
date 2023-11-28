package org.eblood.marketdataservice.quantitative.messaging.producer;

import org.eblood.marketdataservice.configuration.MessagingProperties;
import org.eblood.marketdataservice.quantitative.messaging.Utils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class FXSpotMessageProducer extends FXMessageProducer {

    public FXSpotMessageProducer(RabbitTemplate rabbitTemplate, MessagingProperties messagingProperties) {
        super(rabbitTemplate, messagingProperties);
    }

    @Override
    protected Object getDataSet() {
        return Utils.FX_SPOT_DATASET;
    }
}
