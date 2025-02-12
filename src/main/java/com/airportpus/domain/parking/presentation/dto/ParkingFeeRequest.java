package com.airportpus.domain.parking.presentation.dto;

public record ParkingFeeRequest(
    int holidayMinutes,
    int weekdayMinutes,
    String parkingLot,
    boolean isLargeCar,
    int discountType
) {
}
