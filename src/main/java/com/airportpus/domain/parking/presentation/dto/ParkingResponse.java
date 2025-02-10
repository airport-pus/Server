package com.airportpus.domain.parking.presentation.dto;

import com.airportpus.domain.parking.service.dto.ParkingApiResponse;

public record ParkingResponse(
    String parkingAirportCodeName,
    String parkingCongestion,
    String parkingCongestionDegree,
    int parkingOccupiedSpace,
    int parkingTotalSpace,
    int remainingSpace
) {

  public static ParkingResponse from (ParkingApiResponse.ParkingInfo parkingInfo) {
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
