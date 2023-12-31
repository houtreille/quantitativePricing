package org.eblood.quantitative.mapper;

import com.eblood.finance.quantitative.json.v1.FXOptionDTO;
import com.eblood.finance.quantitative.json.v1.ValuationDTO;
import org.eblood.quantitative.model.option.FXOptionVanilla;
import org.eblood.quantitative.model.valuation.Valuation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JsonMapper {

  FXOptionDTO map(FXOptionVanilla value);
  FXOptionVanilla map(FXOptionDTO value);
  ValuationDTO map(Valuation value);
}
