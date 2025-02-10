package com.airportpus.domain.parking.presentation.dto;

public record ParkingResponse(
    String parkingAirportCodeName,
    String parkingCongestion,
    String parkingCongestionDegree,
    int parkingOccupiedSpace,
    int parkingTotalSpace,
    int remainingSpace
) {}
