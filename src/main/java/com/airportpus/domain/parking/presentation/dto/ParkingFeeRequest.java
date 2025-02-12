package com.airportpus.domain.parking.presentation.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주차 요금 계산 요청 DTO")
public record ParkingFeeRequest(

    @Schema(description = "휴일 주차 시간 (분)", example = "180")
    int holidayMinutes,

    @Schema(description = "평일 주차 시간 (분)", example = "240")
    int weekdayMinutes,

    @Schema(description = "주차장 코드", example = "P1")
    String parkingLot,

    @Schema(description = "대형차 여부", example = "false")
    boolean isLargeCar,

    @Schema(description = "할인 유형 (0: 없음, 1: 경차, 2: 저공해 1·2종, 3: 저공해 3종, 4: 장애인, 5: 다자녀, 6: 국가유공자)", example = "2")
    int discountType

) {
}
