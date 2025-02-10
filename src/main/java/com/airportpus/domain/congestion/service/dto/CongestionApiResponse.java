package com.airportpus.domain.congestion.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record CongestionApiResponse(
    @JsonProperty("currentCount") int currentCount,
    @JsonProperty("data") List<CongestionInfo> data,
    @JsonProperty("matchCount") int matchCount,
    @JsonProperty("page") int page,
    @JsonProperty("perPage") int perPage,
    @JsonProperty("totalCount") int totalCount
) {

  public record CongestionInfo(
      @JsonProperty("CGDR_ALL_LVL") int cgdrAllLvl,
      @JsonProperty("CGDR_A_LVL") int cgdrALvl,
      @JsonProperty("CGDR_B_LVL") int cgdrBLvl,
      @JsonProperty("CGDR_C_LVL") int cgdrCLvl,
      @JsonProperty("IATA_APCD") String iataApCd,
      @JsonProperty("PRC_HR") String prcHr
  ) {}
}

