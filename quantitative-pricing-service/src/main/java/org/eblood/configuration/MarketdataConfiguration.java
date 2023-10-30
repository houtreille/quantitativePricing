package org.eblood.configuration;

import com.eblood.finance.quantitative.marketdata.ApiClient;
import com.eblood.finance.quantitative.marketdata.client.FxSpotApi;
import lombok.var;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MarketdataConfiguration {

    private final MarketdataProperties marketdataProperties;

    public MarketdataConfiguration(MarketdataProperties marketdataProperties) {
        this.marketdataProperties = marketdataProperties;
    }

    @Bean
    FxSpotApi fxSpotApi(@Qualifier("fxSpot")  ApiClient apiClient) {
        return new FxSpotApi(apiClient);
    }

    @Bean(name = "fxSpot")
    public ApiClient apiClientV1() {
        var apiClient = new ApiClient();
        apiClient.setBasePath(marketdataProperties.getUrl());
        return apiClient;
    }

}
