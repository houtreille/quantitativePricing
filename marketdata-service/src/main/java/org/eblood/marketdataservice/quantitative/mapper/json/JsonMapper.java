package org.eblood.marketdataservice.quantitative.mapper.json;

import com.eblood.finance.quantitative.json.v1.FXSpotDTO;
import com.eblood.finance.quantitative.json.v1.FXVolatilityDTO;
import org.eblood.marketdataservice.quantitative.domain.model.entity.FxSpot;
import org.eblood.marketdataservice.quantitative.domain.model.entity.FxVolatilityData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface JsonMapper {

    @Mapping(source="domesticCurr", target="curr1")
    @Mapping(source="foreignCurr", target="curr2")
    @Mapping(source="valueDate", target="date")
    @Mapping(source="value", target="value")
    FXSpotDTO map(FxSpot fxSpot);


    @Mapping(source="curr1", target="domesticCurr")
    @Mapping(source="curr2", target="foreignCurr")
    @Mapping(source="date", target="valueDate")
    @Mapping(source="value", target="value")
    FxSpot map(FXSpotDTO fxSpotDto);

    FXVolatilityDTO mapVolatility(FxVolatilityData fxVolatilityData);

    FxVolatilityData mapVolatility(FXVolatilityDTO fxVolatilityDto);
}

