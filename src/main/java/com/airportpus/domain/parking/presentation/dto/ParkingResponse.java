package com.airportpus.domain.parking.presentation.dto;

import com.airportpus.domain.parking.service.dto.ParkingApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "주차장 정보 응답 DTO")
public record ParkingResponse(

    @Schema(description = "주차장명", example = "P1 여객주차장")
    String parkingAirportCodeName,

    @Schema(description = "주차 혼잡도 상태 (원활, 혼잡, 만차)", example = "혼잡")
    String parkingCongestion,

    @Schema(description = "주차 혼잡도 지수 (0~100)", example = "60")
    String parkingCongestionDegree,

    @Schema(description = "현재 주차된 차량 수", example = "450")
    int parkingOccupiedSpace,

    @Schema(description = "주차장 총 주차 가능 대수", example = "500")
    int parkingTotalSpace,

    @Schema(description = "현재 남은 주차 공간 수", example = "50")
    int remainingSpace

) {

  public static ParkingResponse from(ParkingApiResponse.ParkingInfo parkingInfo) {
    int occupiedSpace = parkingInfo.getOccupiedSpace();
    int totalSpace = parkingInfo.getTotalSpace();
    int remainingSpace = totalSpace - occupiedSpace;

    return new ParkingResponse(
        parkingInfo.getParkingName(),
        parkingInfo.getCongestion(),
        parkingInfo.getCongestionDegree(),
        occupiedSpace,
        totalSpace,
        remainingSpace
    );
  }
}
