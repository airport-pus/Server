package com.airportpus.domain.parking.service.dto;

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
public class ParkingApiResponse {

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
    private List<ParkingInfo> items;

    @JacksonXmlProperty(localName = "numOfRows")
    private int numOfRows;

    @JacksonXmlProperty(localName = "pageNo")
    private int pageNo;

    @JacksonXmlProperty(localName = "totalCount")
    private int totalCount;
  }

  @Getter
  @Setter
  public static class ParkingInfo {

    @JacksonXmlProperty(localName = "airportEng")
    private String airportEng;

    @JacksonXmlProperty(localName = "airportKor")
    private String airportKor;

    @JacksonXmlProperty(localName = "parkingAirportCodeName")
    private String parkingName;

    @JacksonXmlProperty(localName = "parkingCongestion")
    private String congestion;

    @JacksonXmlProperty(localName = "parkingCongestionDegree")
    private String congestionDegree;

    @JacksonXmlProperty(localName = "parkingOccupiedSpace")
    private int occupiedSpace;

    @JacksonXmlProperty(localName = "parkingTotalSpace")
    private int totalSpace;

    @JacksonXmlProperty(localName = "sysGetdate")
    private String sysGetdate;

    @JacksonXmlProperty(localName = "sysGettime")
    private String sysGettime;
  }
}
