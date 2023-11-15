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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomesticCurr() {
        return domesticCurr;
    }

    public void setDomesticCurr(String domesticCurr) {
        this.domesticCurr = domesticCurr;
    }

    public String getForeignCurr() {
        return foreignCurr;
    }

    public void setForeignCurr(String foreignCurr) {
        this.foreignCurr = foreignCurr;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

}
