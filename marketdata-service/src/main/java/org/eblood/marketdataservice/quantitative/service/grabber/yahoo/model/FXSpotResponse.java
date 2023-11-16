package org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FXSpotResponse {
    private Meta meta;
    private Map<String, Body> body;

    @JsonIgnoreProperties
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        private String processedTime;
        private String currency;
        private String symbol;
        private String exchangeName;
        private String instrumentType;
        private Number firstTradeDate;
        private Number regularMarketTime;
        private Number gmtoffset;
        private String timezone;
        private String exchangeTimezoneName;
        private Number regularMarketPrice;
        private Number chartPreviousClose;
        private Number priceHint;
        private String dataGranularity;
        private String range;
        private String version;
        private Number status;
        private String copywrite;
    }
    @JsonIgnoreProperties
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private String date;
        private Number date_utc;
        private Number open;
        private Number high;
        private Number low;
        private Number close;
        private Number volume;
        private Number adjclose;
    }
}
