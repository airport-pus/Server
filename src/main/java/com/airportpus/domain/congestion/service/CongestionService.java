package com.airportpus.domain.congestion.service;

import com.airportpus.common.config.ApiProperties;
import com.airportpus.domain.congestion.domain.repository.CongestionRepository;
import com.airportpus.domain.congestion.presentation.dto.CongestionResponse;
import com.airportpus.domain.congestion.service.dto.CongestionApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CongestionService {

  private final CongestionRepository congestionRepository;
  private final WebClient congestionWebClient;
  private final ApiProperties apiProperties;

  public List<CongestionResponse> getAll() {
    return congestionRepository.findAll().stream()
        .map(CongestionResponse::fromByCongestion)
        .toList();
  }

  public CongestionResponse getCongestionRealTime() {
    return congestionWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("cond[IATA_APCD::EQ]", "PUS")
            .queryParam("serviceKey", apiProperties.getServiceKey())
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(CongestionApiResponse.class)
        .map(response -> response.data().get(0))
        .map(CongestionResponse::fromByInfo)
        .block();
  }
}
