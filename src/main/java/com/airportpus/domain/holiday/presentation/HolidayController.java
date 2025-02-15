package com.airportpus.domain.holiday.presentation;

import com.airportpus.domain.holiday.presentation.dto.HolidayResponse;
import com.airportpus.domain.holiday.service.HolidayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Holiday API", description = "휴일 API")
@RestController
@RequestMapping("/holiday")
@RequiredArgsConstructor
public class HolidayController {

  private final HolidayService holidayService;

  @Operation(summary = "휴일 정보 조회", description = "휴일 정보를 조회합니다.")
  @GetMapping(produces = "application/json")
  public List<HolidayResponse> getHoliday() {
    return holidayService.getHoliday();
  }
}
