package com.airportpus.domain.holiday.service;

import com.airportpus.common.config.ApiProperties;
import com.airportpus.domain.holiday.presentation.dto.HolidayResponse;
import com.airportpus.domain.holiday.service.dto.HolidayApiResponse;
import com.airportpus.domain.parking.exception.XmlParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HolidayService {

  private final WebClient holidayWebClient;
  private final ApiProperties apiProperties;
  private final XmlMapper xmlMapper = new XmlMapper();

  public List<HolidayResponse> getHoliday() {
    return holidayWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("serviceKey", apiProperties.getServiceKey())
            .queryParam("numOfRows", "100")
            .queryParam("solYear", LocalDate.now().getYear())
            .build())
        .accept(MediaType.APPLICATION_XML)
        .retrieve()
        .bodyToMono(String.class)
        .map(this::convertXmlToHolidayResponse)
        .block();
  }

  private List<HolidayResponse> convertXmlToHolidayResponse(String xml) {
    HolidayApiResponse holidayApiResponse;
    try {
      holidayApiResponse = xmlMapper.readValue(xml, HolidayApiResponse.class);
    } catch (JsonProcessingException e) {
      throw new XmlParsingException();
    }

    return holidayApiResponse.getBody().getItems().stream()
        .map(HolidayResponse::from)
        .toList();
  }
}
