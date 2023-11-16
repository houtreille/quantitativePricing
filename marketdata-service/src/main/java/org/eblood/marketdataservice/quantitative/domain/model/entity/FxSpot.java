package org.eblood.marketdataservice.quantitative.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "fx_spot")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FxSpot {
    @Id
    @Column(name = "KEY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DOMESTIC_CURR", nullable = false)
    String domesticCurr;

    @Column(name = "FOREIGN_CURR", nullable = false)
    String foreignCurr;

    @Column(name = "VALUE_DATE", nullable = false)
    LocalDate valueDate;

    @Column(name = "VALUE")
    Double value;

    @Column(name = "HIGH")
    Double high;

    @Column(name = "LOW")
    Double low;

    @Column(name = "VOLUME")
    Double volume;

    @Column(name = "EXCHANGE_NAME")
    String exchangeName;
}
