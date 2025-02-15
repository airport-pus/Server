package com.airportpus.domain.congestion.presentation;

import com.airportpus.domain.congestion.presentation.dto.RealCongestionResponse;
import com.airportpus.domain.congestion.presentation.dto.SaveCongestionResponse;
import com.airportpus.domain.congestion.service.CongestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/congestions")
@RequiredArgsConstructor
@Tag(name = "Congestion API", description = "공항 혼잡도 API")
public class CongestionController {

  private final CongestionService congestionService;

  @Operation(
      summary = "저장된 공항 혼잡도 조회",
      description = "저장된 전체 구간 및 각 구간(1구간, 2구간, 3구간)의 혼잡도 정보를 반환합니다."
  )
  @GetMapping(produces = "application/json")
  public List<SaveCongestionResponse> getCongestion() {
    return congestionService.getAll();
  }

  @Operation(
      summary = "실시간 공항 혼잡도 조회",
      description = "전체 구간 및 각 구간(1구간, 2구간, 3구간)의 혼잡도 정보를 반환합니다."
  )
  @GetMapping(value = "/real", produces = "application/json")
  public RealCongestionResponse getCongestionByReal() {
    return congestionService.getCongestionRealTime();
  }
}
