package com.airportpus.domain.apron.service;

import com.airportpus.common.config.ApiProperties;
import com.airportpus.domain.apron.presentation.dto.ApronInResponse;
import com.airportpus.domain.apron.presentation.dto.ApronOutResponse;
import com.airportpus.domain.apron.service.dto.ApronApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApronService {

  private final WebClient apronWebClient;
  private final ApiProperties apiProperties;
  private final XmlMapper xmlMapper = new XmlMapper();

  public List<?> getApronByIo(String type) {
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    String upCaseType = type.toUpperCase();

    return apronWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("serviceKey", apiProperties.getServiceKey())
            .queryParam("numOfRows", 250)
            .queryParam("flightdate", today)
            .queryParam("airport", "PUS")
            .queryParam("io", upCaseType)
            .build())
        .accept(MediaType.APPLICATION_XML)
        .retrieve()
        .bodyToMono(String.class)
        .map(xml -> convertXmlToApronResponse(xml, upCaseType))
        .block();
  }

  public List<?> getApronByFlightNumber(String flightNumber) {
    String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    return apronWebClient.get()
        .uri(uriBuilder -> uriBuilder
            .queryParam("serviceKey", apiProperties.getServiceKey())
            .queryParam("numOfRows", 250)
            .queryParam("flightdate", today)
            .queryParam("airport", "PUS")
            .queryParam("airfln", flightNumber)
            .build())
        .accept(MediaType.APPLICATION_XML)
        .retrieve()
        .bodyToMono(String.class)
        .map(this::convertXmlToApronResponse)
        .block();
  }

  private List<?> convertXmlToApronResponse(String xml, String type) {
    ApronApiResponse apronApiResponse = parseXml(xml);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
    LocalTime nowPlus10Minutes = LocalTime.now().plusMinutes(10);

    return apronApiResponse.getBody().getItems().stream()
        .filter(info -> {
          String flightTime = getFlightTime(info);
          if (flightTime == null) return true;

          LocalTime flightTimeParsed = LocalTime.parse(flightTime, formatter);
          return flightTimeParsed.isAfter(nowPlus10Minutes);
        })
        .sorted(Comparator.comparing(info -> {
          String flightTime = getFlightTime(info);
          if (flightTime == null) return LocalTime.MAX;
          try {
            return LocalTime.parse(flightTime, formatter);
          } catch (Exception e) {
            return LocalTime.MAX;
          }
        }))
        .map(info -> "I".equals(type) ? ApronInResponse.from(info) : ApronOutResponse.from(info))
        .toList();
  }

  private List<?> convertXmlToApronResponse(String xml) {
    ApronApiResponse apronApiResponse = parseXml(xml);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
    LocalTime nowPlus10Minutes = LocalTime.now().plusMinutes(10);

    return apronApiResponse.getBody().getItems().stream()
        .filter(info -> {
          String flightTime = getFlightTime(info);
          if (flightTime == null) return true;

          LocalTime flightTimeParsed = LocalTime.parse(flightTime, formatter);
          return flightTimeParsed.isAfter(nowPlus10Minutes);
        })
        .sorted(Comparator.comparing(info -> {
          String flightTime = getFlightTime(info);
          if (flightTime == null) return LocalTime.MAX;
          try {
            return LocalTime.parse(flightTime, formatter);
          } catch (Exception e) {
            return LocalTime.MAX;
          }
        }))
        .map(info -> "I".equals(info.getIo()) ? ApronInResponse.from(info) : ApronOutResponse.from(info))
        .toList();
  }

  private ApronApiResponse parseXml(String xml) {
    try {
      return xmlMapper.readValue(xml, ApronApiResponse.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String getFlightTime(ApronApiResponse.ApronInfo info) {
    return (info.getEtd() != null && !info.getEtd().isBlank()) ? info.getEtd() : info.getStd();
  }
}
