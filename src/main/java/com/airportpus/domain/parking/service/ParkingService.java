package com.airportpus.domain.parking.service;

import com.airportpus.domain.parking.exception.XmlParsingException;
import com.airportpus.domain.parking.presentation.dto.ParkingResponse;
import com.airportpus.domain.parking.service.dto.ParkingApiResponse;
import com.airportpus.common.config.ApiProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

  private final WebClient parkingWebClient;
  private final ApiProperties apiProperties;
  private final XmlMapper xmlMapper = new XmlMapper();

  public List<ParkingResponse> getParking() {
    return parkingWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("serviceKey", apiProperties.getServiceKey())
            .queryParam("schAirportCode", "PUS")
            .build())
        .accept(MediaType.APPLICATION_XML)
        .retrieve()
        .bodyToMono(String.class)
        .map(this::convertXmlToParkingResponse)
        .block();
  }

  private List<ParkingResponse> convertXmlToParkingResponse(String xml) {
    ParkingApiResponse parkingApiResponse;
    try {
      parkingApiResponse = xmlMapper.readValue(xml, ParkingApiResponse.class);
    } catch (JsonProcessingException e) {
      throw new XmlParsingException();
    }

    return parkingApiResponse.getBody().getItems().stream()
          .map(ParkingResponse::from)
          .toList();
  }
}
