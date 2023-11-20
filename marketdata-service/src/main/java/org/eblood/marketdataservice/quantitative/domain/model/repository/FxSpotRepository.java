package org.eblood.marketdataservice.quantitative.domain.model.repository;

import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface FxSpotRepository extends JpaRepository<FxSpot, Long> {

   List<FxSpot> findAll();

   List<FxSpot> findFxSpotByDomesticCurrAndForeignCurr(String domesticCurr, String foreignCurr);

   FxSpot getFxSpotByDomesticCurrAndForeignCurrAndValueDate(String domesticCurr, String foreignCurr, LocalDate valueDate);


   FxSpot findFirstByDomesticCurrAndForeignCurrOrderByValueDateDesc(String domesticCurr, String foreignCurr);

  // FxSpot findFirstByValueDateAndDomesticCurrAndForeignCurr(String domesticCurr, String foreignCurr);

   @Override
   FxSpot save(FxSpot fxSpot);

   @Override
   <S extends FxSpot> List<S> saveAll(Iterable<S> entities);
}
