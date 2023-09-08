package org.eblood.quantitative.model.option;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.eblood.quantitative.model.ProductEnum;
import org.eblood.quantitative.model.StructuredProduct;

@Data
@Builder
@EqualsAndHashCode
public class FXOptionVanilla implements StructuredProduct {
  private String curr1;
  private String curr2;
  private LocalDate maturityDate;
  private Double strike;

  @Override
  public ProductEnum getProductType() {
    return ProductEnum.FX_OPTION_VANILLA;
  }
}
