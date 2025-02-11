package com.airportpus.domain.apron.exception;

import com.airportpus.common.exception.AirportPusException;
import org.springframework.http.HttpStatus;

public class FlightNumberNotFoundException extends AirportPusException {
  public FlightNumberNotFoundException() {
    super(HttpStatus.NOT_FOUND, "FLIGHT_NUMBER_NOT_FOUND", "항공편명을 찾을 수 없습니다.");
  }
}
