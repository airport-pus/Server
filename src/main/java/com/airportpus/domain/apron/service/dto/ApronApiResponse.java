package com.airportpus.domain.apron.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApronApiResponse {

  @JacksonXmlProperty(localName = "header")
  private Header header;

  @JacksonXmlProperty(localName = "body")
  private Body body;

  @Getter
  @Setter
  public static class Header {
    @JacksonXmlProperty(localName = "resultCode")
    private String resultCode;

    @JacksonXmlProperty(localName = "resultMsg")
    private String resultMsg;
  }

  @Getter
  @Setter
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class Body {
    @JacksonXmlElementWrapper(localName = "items")
    @JacksonXmlProperty(localName = "item")
    private List<FlightInfo> items;

    @JacksonXmlProperty(localName = "numOfRows")
    private int numOfRows;

    @JacksonXmlProperty(localName = "pageNo")
    private int pageNo;

    @JacksonXmlProperty(localName = "totalCount")
    private int totalCount;
  }

  @Getter
  @Setter
  public static class FlightInfo {
    @JacksonXmlProperty(localName = "ufid")
    private String ufid;

    @JacksonXmlProperty(localName = "flightdate")
    private String flightDate;

    @JacksonXmlProperty(localName = "airfln")
    private String flightNumber;

    @JacksonXmlProperty(localName = "airlineEnglish")
    private String airlineEnglish;

    @JacksonXmlProperty(localName = "airlineKorean")
    private String airlineKorean;

    @JacksonXmlProperty(localName = "boardingEng")
    private String boardingEng;

    @JacksonXmlProperty(localName = "boardingKor")
    private String boardingKor;

    @JacksonXmlProperty(localName = "boardingJpn")
    private String boardingJpn;

    @JacksonXmlProperty(localName = "boardingChn")
    private String boardingChn;

    @JacksonXmlProperty(localName = "arrivedEng")
    private String arrivedEng;

    @JacksonXmlProperty(localName = "arrivedKor")
    private String arrivedKor;

    @JacksonXmlProperty(localName = "arrivedChn")
    private String arrivedChn;

    @JacksonXmlProperty(localName = "arrivedJpn")
    private String arrivedJpn;

    @JacksonXmlProperty(localName = "city")
    private String city;

    @JacksonXmlProperty(localName = "airport")
    private String airportCode;

    @JacksonXmlProperty(localName = "std")
    private String std;

    @JacksonXmlProperty(localName = "etd")
    private String etd;

    @JacksonXmlProperty(localName = "io")
    private String io;

    @JacksonXmlProperty(localName = "line")
    private String line;

    @JacksonXmlProperty(localName = "baggageClaim")
    private String baggageClaim;

    @JacksonXmlProperty(localName = "gate")
    private String gate;

    @JacksonXmlProperty(localName = "rmkEng")
    private String remarkEng;

    @JacksonXmlProperty(localName = "rmkKor")
    private String remarkKor;

    @JacksonXmlProperty(localName = "rmkChn")
    private String remarkChn;

    @JacksonXmlProperty(localName = "rmkJpn")
    private String remarkJpn;

    @JacksonXmlProperty(localName = "sptNm")
    private String sptNm;

    @JacksonXmlProperty(localName = "lineCode")
    private String lineCode;
  }
}
