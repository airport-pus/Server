package com.airportpus.domain.apron.presentation.dto;

import com.airportpus.domain.apron.service.dto.ApronApiResponse;

public record ApronOutResponse(
    String flightNumber,
    String airlineEnglish,
    String airlineKorean,
    String arrivedEng,
    String arrivedKor,
    String gate,
    String boardingEng,
    String boardingKor,
    String std,
    String etd,
    String io,
    String line,
    String remarkEng,
    String remarkKor
) {

  public static ApronOutResponse from(ApronApiResponse.FlightInfo info) {
    return new ApronOutResponse(
        info.getFlightNumber(),
        info.getAirlineEnglish(),
        info.getAirlineKorean(),
        info.getArrivedEng(),
        info.getArrivedKor(),
        info.getGate(),
        info.getBoardingEng(),
        info.getBoardingKor(),
        info.getStd(),
        info.getEtd(),
        info.getIo(),
        info.getLine(),
        info.getRemarkEng(),
        info.getRemarkKor()
    );
  }
}