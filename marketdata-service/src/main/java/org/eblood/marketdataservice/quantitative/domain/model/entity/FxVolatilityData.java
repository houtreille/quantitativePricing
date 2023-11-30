package org.eblood.marketdataservice.quantitative.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fx_volatility")
public class FxVolatilityData {

    @Id
    @Column(name = "KEY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "CURRENCY_PAIR", nullable = false)
    String currencyPair;

    @Column(name = "VALUATION_DATE", nullable = false)
    Date valuationDate;

    @Column(name = "PROVIDER")
    String provider;

    @Column(name = "TENOR", nullable = false)
    LocalDate tenor;

    @Column(name = "STRIKE", nullable = false)
    Double strike;

    @Column(name = "VOLATILITY")
    Double volatility;

}
