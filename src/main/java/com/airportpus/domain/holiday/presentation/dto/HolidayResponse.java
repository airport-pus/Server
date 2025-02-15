package com.airportpus.domain.holiday.presentation.dto;

import com.airportpus.domain.holiday.service.dto.HolidayApiResponse;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "휴일 응답 DTO")
public record HolidayResponse(
    @Schema(description = "휴일날짜", example = "20250101") String holiday
) {

  public static HolidayResponse from(HolidayApiResponse.HolidayInfo info) {
    return new HolidayResponse(info.getLocdate());
  }
}
