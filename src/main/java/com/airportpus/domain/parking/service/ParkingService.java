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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParkingService {

  private final WebClient webClient;
  private final ApiProperties apiProperties;
  private final XmlMapper xmlMapper = new XmlMapper();

  public List<ParkingResponse> getParkingInfo() {
    return webClient.get()
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
    ParkingApiResponse parkingApiResponse = null;
    try {
      parkingApiResponse = xmlMapper.readValue(xml, ParkingApiResponse.class);
    } catch (JsonProcessingException e) {
      throw new XmlParsingException();
    }

    return parkingApiResponse.getBody().getItems().stream()
          .map(this::toParkingResponse)
          .collect(Collectors.toList());
  }

  private ParkingResponse toParkingResponse(ParkingApiResponse.ParkingInfo parkingInfo) {
    int occupiedSpace = parkingInfo.getOccupiedSpace();
    int totalSpace = parkingInfo.getTotalSpace();
    int remainingSpace = totalSpace - occupiedSpace;

    return new ParkingResponse(
        parkingInfo.getParkingName(),
        parkingInfo.getCongestion(),
        parkingInfo.getCongestionDegree(),
        occupiedSpace,
        totalSpace,
        remainingSpace
    );
  }
}
