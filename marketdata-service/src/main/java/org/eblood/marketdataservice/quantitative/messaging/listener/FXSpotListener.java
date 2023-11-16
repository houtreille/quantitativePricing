package org.eblood.marketdataservice.quantitative.messaging.listener;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.service.FxSpotService;
import org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api.FXSpotAPIClient;
import org.slf4j.Logger;
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

    @RabbitListener(queues = "myQueue")
    public void listen(String in)  {
        if(logger.isDebugEnabled()) {
            logger.debug("Message read from myQueue : " + in);
        }

        List<FxSpot> fxSpots = null;
        try {
            fxSpots = this.fxSpotService.getFxSpotHistory();
            Object a = this.fxSpotService.saveAll(fxSpots);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println(fxSpots);
    }

}
