package org.eblood.quantitative.model;

public enum ProductEnum {

  FX_OPTION_VANILLA("Fx Option Vanilla"),
  UNKNOWN("Unknown product");

  String description;

  ProductEnum(String description) {
    this.description = description;
  }
}
