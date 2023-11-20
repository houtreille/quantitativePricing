package org.eblood.marketdataservice.quantitative.service.grabber.yahoo.model.api;

public class YahooFXSpotAPIClientException extends Exception {

    public YahooFXSpotAPIClientException(String message) {
        super(message);
    }

    public YahooFXSpotAPIClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public YahooFXSpotAPIClientException(Throwable cause) {
        super(cause);
    }

    public YahooFXSpotAPIClientException() {
        super();
    }
}
