package com.airportpus.domain.parking.exception;

import com.airportpus.common.exception.AirportPusException;
import org.springframework.http.HttpStatus;

public class ParkingLotNotFoundException extends AirportPusException {
  public ParkingLotNotFoundException() {
    super(HttpStatus.NOT_FOUND, "PARKING_LOT_NOT_FOUND", "주차장을 찾을 수 없습니다.");
  }
}
