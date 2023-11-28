package org.eblood.marketdataservice.configuration;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("messaging")
public class MessagingProperties {


    FXExchange fxExchange = new FXExchange();

    TopicExchange topicExchange = new TopicExchange();

    public FXExchange getFxExchange() {
        return fxExchange;
    }

    public void setFxExchange(FXExchange fxSpotHistoryRequestExchange) {
        this.fxExchange = fxSpotHistoryRequestExchange;
    }

    public TopicExchange getTopicExchange() {
        return topicExchange;
    }

    public void setTopicExchange(TopicExchange topicExchange) {
        this.topicExchange = topicExchange;
    }


    public static class FXExchange {

        private String name;

        private Queue spotQueue;

        private Queue volatilityQueue;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        public Queue getSpotQueue() {
            return spotQueue;
        }

        public void setSpotQueue(Queue spotQueue) {
            this.spotQueue = spotQueue;
        }

        public Queue getVolatilityQueue() {
            return volatilityQueue;
        }

        public void setVolatilityQueue(Queue volatilityQueue) {
            this.volatilityQueue = volatilityQueue;
        }
    }

    public static class TopicExchange {


        private String name;

        private TopicQueue testQueue1;

        private TopicQueue testQueue2;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TopicQueue getTestQueue1() {
            return testQueue1;
        }

        public void setTestQueue1(TopicQueue testQueue1) {
            this.testQueue1 = testQueue1;
        }

        public TopicQueue getTestQueue2() {
            return testQueue2;
        }

        public void setTestQueue2(TopicQueue testQueue2) {
            this.testQueue2 = testQueue2;
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

    public static class TopicQueue {
        private String name;
        private String keyPatterns;
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKeyPatterns() {
            return keyPatterns;
        }

        public void setKeyPatterns(String keyPatterns) {
            this.keyPatterns = keyPatterns;
        }

    }

}
