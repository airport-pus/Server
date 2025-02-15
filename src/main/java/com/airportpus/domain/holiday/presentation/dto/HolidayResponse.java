package com.airportpus.domain.holiday.presentation.dto;

import com.airportpus.domain.holiday.service.dto.HolidayApiResponse;

public record HolidayResponse(
    String holiday
) {

  public static HolidayResponse from(HolidayApiResponse.HolidayInfo info) {
    return new HolidayResponse(info.getLocdate());
  }
}
