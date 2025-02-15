package com.airportpus.domain.holiday.service.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HolidayApiResponse {

  @JacksonXmlProperty(localName = "header")
  private Header header;

  @JacksonXmlProperty(localName = "body")
  private Body body;

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Header {
    @JacksonXmlProperty(localName = "resultCode")
    private String resultCode;

    @JacksonXmlProperty(localName = "resultMsg")
    private String resultMsg;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class Body {
    @JacksonXmlElementWrapper(localName = "items")
    @JacksonXmlProperty(localName = "item")
    private List<HolidayInfo> items;

    @JacksonXmlProperty(localName = "numOfRows")
    private int numOfRows;

    @JacksonXmlProperty(localName = "pageNo")
    private int pageNo;

    @JacksonXmlProperty(localName = "totalCount")
    private int totalCount;
  }

  @Getter
  @Setter
  @NoArgsConstructor
  public static class HolidayInfo {
    @JacksonXmlProperty(localName = "dateKind")
    private String dateKind;

    @JacksonXmlProperty(localName = "dateName")
    private String dateName;

    @JacksonXmlProperty(localName = "isHoliday")
    private String isHoliday;

    @JacksonXmlProperty(localName = "locdate")
    private String locdate;

    @JacksonXmlProperty(localName = "seq")
    private int seq;
  }
}
