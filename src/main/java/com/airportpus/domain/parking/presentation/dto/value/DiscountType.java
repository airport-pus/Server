package com.airportpus.domain.parking.presentation.dto.value;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
@Schema(description = "할인 유형을 나타내는 Enum")
public enum DiscountType {

  @Schema(description = "일반 (할인 없음)", example = "0")
  NONE(0, 0),

  @Schema(description = "경차 (50% 할인)", example = "1")
  COMPACT_CAR(1, 50),

  @Schema(description = "저공해 자동차 1·2종 (50% 할인)", example = "2")
  ECO_CAR_1_2(2, 50),

  @Schema(description = "저공해 자동차 3종 (20% 할인)", example = "3")
  ECO_CAR_3(3, 20),

  @Schema(description = "장애인 등록차량 (50% 할인)", example = "4")
  DISABLED(4, 50),

  @Schema(description = "다자녀 (50% 할인)", example = "5")
  MULTI_CHILD(5, 50),

  @Schema(description = "국가 유공자 (50% 할인)", example = "6")
  NATIONAL_MERIT(6, 50);

  private final int code;
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
