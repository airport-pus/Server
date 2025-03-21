package com.airportpus.domain.congestion.service;

import com.airportpus.common.config.ApiProperties;
import com.airportpus.domain.congestion.domain.repository.CongestionRepository;
import com.airportpus.domain.congestion.presentation.dto.RealCongestionResponse;
import com.airportpus.domain.congestion.presentation.dto.SaveCongestionResponse;
import com.airportpus.domain.congestion.service.dto.CongestionApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CongestionService {

  private final CongestionRepository congestionRepository;
  private final WebClient congestionWebClient;
  private final ApiProperties apiProperties;

  public List<SaveCongestionResponse> getAll() {
    return congestionRepository.findAll().stream()
        .map(SaveCongestionResponse::from)
        .toList();
  }

  public RealCongestionResponse getCongestionRealTime() {
    return congestionWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("cond[IATA_APCD::EQ]", "PUS")
            .queryParam("serviceKey", apiProperties.getServiceKey())
            .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(CongestionApiResponse.class)
        .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(5)))
        .map(response -> response.data().isEmpty() ?
            new RealCongestionResponse(0, 0, 0, 0) :
            RealCongestionResponse.from(response.data().get(0)))
        .block();
  }
}
