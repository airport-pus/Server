package com.airportpus.domain.parking.presentation.dto.value;

import lombok.Getter;

@Getter
public enum DiscountType {
  NONE(0, 0),
  COMPACT_CAR(1, 50),
  ECO_CAR_1_2(2, 50),
  ECO_CAR_3(3, 20),
  DISABLED(4, 50),
  MULTI_CHILD(5, 50),
  NATIONAL_MERIT(6, 50);

  private final int code;
  @Getter
  private final int discountRate;

  DiscountType(int code, int discountRate) {
    this.code = code;
    this.discountRate = discountRate;
  }

  public static DiscountType fromCode(int code) {
    for (DiscountType type : values()) {
      if (type.code == code) {
        return type;
      }
    }
    return NONE;
  }
}


