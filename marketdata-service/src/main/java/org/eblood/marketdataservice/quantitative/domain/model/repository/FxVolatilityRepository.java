package org.eblood.marketdataservice.quantitative.domain.model.repository;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.entity.FxVolatilityData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FxVolatilityRepository extends JpaRepository<FxVolatilityData, Long> {
    public List<FxVolatilityData> findAll();

    public List<FxVolatilityData> findFxVolatilityDataByCurrencyPairAndValuationDate(String currencyPair, String valueDate);

}
