package com.airportpus.domain.apron.presentation;

import com.airportpus.domain.apron.service.ApronService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apron")
@RequiredArgsConstructor
@Tag(name = "Apron API", description = "주기장 API")
public class ApronController {

  private final ApronService apronService;

  @Operation(
      summary = "항공편 목록 조회",
      description = "입/출국 구분(io)에 따라 주기장 항공편 목록을 조회합니다."
  )
  @GetMapping(produces = "application/json")
  public List<?> getApron(
      @Parameter(description = "입/출국 구분: I(도착), O(출발)", required = true, example = "I")
      @RequestParam("io") String type) {
    return apronService.getApronInfo(type);
  }

  @Operation(
      summary = "항공편 상세 조회",
      description = "항공편 번호(flightNumber)를 기준으로 주기장 항공편 정보를 조회합니다. 결과는 단일 항공편 정보입니다."
  )
  @GetMapping(value = "/flight", produces = "application/json")
  public Optional<?> getApronByFlightNumber(
      @Parameter(description = "항공편 번호", required = true, example = "KE123")
      @RequestParam("flightNumber") String flightNumber) {
    return apronService.getApronInfoByFlightNumber(flightNumber);
  }
}
