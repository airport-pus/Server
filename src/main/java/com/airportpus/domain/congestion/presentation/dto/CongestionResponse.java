package com.airportpus.domain.congestion.presentation.dto;

import com.airportpus.domain.congestion.service.dto.CongestionApiResponse;

public record CongestionResponse(
    int cgdrAllLvl,
    int cgdrALvl,
    int cgdrBLvl,
    int cgdrCLvl
) {

  public static CongestionResponse from(CongestionApiResponse.CongestionInfo info) {
    return new CongestionResponse(
        info.cgdrAllLvl(),
        info.cgdrALvl(),
        info.cgdrBLvl(),
        info.cgdrCLvl()
    );
  }
}
