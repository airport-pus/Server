package com.airportpus.domain.congestion.presentation;

import com.airportpus.domain.congestion.presentation.dto.CongestionResponse;
import com.airportpus.domain.congestion.service.CongestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/congestion")
@RequiredArgsConstructor
public class CongestionController {

  private final CongestionService congestionService;

  @GetMapping(produces = "application/json")
  public CongestionResponse getCongestion() {
    return congestionService.getCongestionInfo();
  }
}
