package org.eblood.quantitative.model.valuation;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class Valuation {
    private String currency;
    private LocalDate valuationDate;
    private Double value;
}
