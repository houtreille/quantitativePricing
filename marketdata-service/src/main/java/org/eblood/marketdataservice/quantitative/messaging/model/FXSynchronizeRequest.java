package org.eblood.marketdataservice.quantitative.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FXSynchronizeRequest {

    public static String FULL_HISTORY = "FULL_HISTORY";
    public static String MISSING_HISTORY = "MISSING_HISTORY";
    public static String YAHOO_PROVIDER = "FX_SPOT";

    private String type;
    private String currencyPair;
    private String provider;
}
