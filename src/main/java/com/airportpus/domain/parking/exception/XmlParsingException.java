package com.airportpus.domain.parking.exception;

import com.airportpus.common.exception.AirportPusException;
import org.springframework.http.HttpStatus;

public class XmlParsingException extends AirportPusException {
  public XmlParsingException() {
    super(HttpStatus.INTERNAL_SERVER_ERROR, "XML_PARSING_ERROR", "XML 파싱에 실패했습니다.");
  }
}
