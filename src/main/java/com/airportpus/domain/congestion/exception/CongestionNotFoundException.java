package com.airportpus.domain.congestion.exception;

import com.airportpus.common.exception.AirportPusException;
import org.springframework.http.HttpStatus;

public class CongestionNotFoundException extends AirportPusException {
  public CongestionNotFoundException() {
    super(HttpStatus.NOT_FOUND, "CONGESTION_NOT_FOUND", "혼잡도를 찾을 수 없습니다.");
  }
}
