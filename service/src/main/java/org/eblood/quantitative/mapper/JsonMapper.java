package org.eblood.quantitative.mapper;

import com.eblood.finance.quantitative.json.v1.FXOptionDTO;
import org.eblood.quantitative.model.option.FXOptionVanilla;
import org.mapstruct.Mapper;

@Mapper
public interface JsonMapper {

  FXOptionDTO map(FXOptionVanilla value);
  FXOptionVanilla map(FXOptionDTO value);


}
