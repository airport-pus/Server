package com.airportpus.domain.parking.service.dto;

public class ParkingFeeResponse {

  // [P1, P2] 요금 (동일 적용)
  // 평일 24시간 요금
  public static final int SMALL_P12_WEEKDAY_24H = 10000;
  public static final int LARGE_P12_WEEKDAY_24H = 9000;

  // 휴일 24시간 요금
  public static final int SMALL_P12_HOLIDAY_24H = 15000;
  public static final int LARGE_P12_HOLIDAY_24H = 13000;

  // P3 요금
  // 평일 24시간 요금
  public static final int SMALL_P3_WEEKDAY_24H = 7000;
  public static final int LARGE_P3_WEEKDAY_24H = 5000;

  // 휴일 24시간 요금
  public static final int SMALL_P3_HOLIDAY_24H = 10000;
  public static final int LARGE_P3_HOLIDAY_24H = 7000;

  // 기본 30분 및 추가 요금 (평일/휴일 모두 동일 방식으로 계산)
  public static final int BASE_TIME = 30; // 분
  public static final int SMALL_BASE_COST = 900;    // 소형차 기본 30분 요금
  public static final int LARGE_BASE_COST = 1200;   // 대형차 기본 30분 요금
  public static final int SMALL_ADDITIONAL_COST = 300; // 소형차 10분 추가 요금
  public static final int LARGE_ADDITIONAL_COST = 400; // 대형차 10분 추가 요금
}
