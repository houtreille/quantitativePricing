package org.eblood.marketdataservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("messaging")
public class MessagingProperties {


    Exchange fxSpotHistoryRequestExchange = new Exchange();

    Exchange fxVolatilityHistoryRequestExchange = new Exchange();
    public Exchange getFxSpotHistoryRequestExchange() {
        return fxSpotHistoryRequestExchange;
    }

    public void setFxSpotHistoryRequestExchange(Exchange fxSpotHistoryRequestExchange) {
        this.fxSpotHistoryRequestExchange = fxSpotHistoryRequestExchange;
    }
    public Exchange getFxVolatilityHistoryRequestExchange() {
        return fxVolatilityHistoryRequestExchange;
    }

    public void setFxVolatilityHistoryRequestExchange(Exchange fxVolatilityHistoryRequestExchange) {
        this.fxVolatilityHistoryRequestExchange = fxVolatilityHistoryRequestExchange;
    }


    public static class Exchange {

        private String name;

        private Queue queue;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Queue getQueue() {
            return queue;
        }

        public void setQueue(Queue queue) {
            this.queue = queue;
        }
    }


    public static class Queue {

        private String name;
        private String contentType;
        private String dataset;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContentType() {
            return contentType;
        }

        public void setContentType(String contentType) {
            this.contentType = contentType;
        }

        public String getDataset() {
            return dataset;
        }

        public void setDataset(String dataset) {
            this.dataset = dataset;
        }
    }

}
