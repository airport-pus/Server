package com.airportpus.domain.parking.service;

import com.airportpus.domain.parking.exception.ParkingLotNotFoundException;
import com.airportpus.domain.parking.presentation.dto.value.DiscountType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.airportpus.domain.parking.service.dto.ParkingFeeResponse.*;

@Slf4j
@Service
public class ParkingFeeService {

  public int getTotalFee(int holidayMinutes, int weekdayMinutes, String parkingLot, boolean isLargeCar, int discountType) {
    log.info("=== 계산 시작 ===");
    log.info("휴일 분: {}, 평일 분: {}, 주차장: {}, 대형차 여부: {}", holidayMinutes, weekdayMinutes, parkingLot, isLargeCar);

    if (holidayMinutes + weekdayMinutes <= 20) {
      log.info("getTotalFee - 전체 주차 시간이 {}분 이하이므로 무료 처리", holidayMinutes + weekdayMinutes);
      return 0;
    }

    int holidayFullDays = holidayMinutes / 1440;
    int weekdayFullDays = weekdayMinutes / 1440;

    int holidayDayRate = getHolidayDayRate(parkingLot, isLargeCar);
    int weekdayDayRate = getWeekdayDayRate(parkingLot, isLargeCar);

    int feeForHolidayFullDays = holidayFullDays * holidayDayRate;
    int feeForWeekdayFullDays = weekdayFullDays * weekdayDayRate;

    log.info("getTotalFee - 휴일 fullDays: {}일, 요금: {}원, 평일 fullDays: {}일, 요금: {}원",
        holidayFullDays, feeForHolidayFullDays, weekdayFullDays, feeForWeekdayFullDays);

    int holidayRemaining = holidayMinutes % 1440;
    int weekdayRemaining = weekdayMinutes % 1440;
    int combinedRemaining = holidayRemaining + weekdayRemaining;
    log.info("getTotalFee - 휴일 남은 분: {}분, 평일 남은 분: {}분, 합계: {}분", holidayRemaining, weekdayRemaining, combinedRemaining);

    int remainingFee = getRemainingFee(combinedRemaining, isLargeCar);
    log.info("getTotalFee - 남은 시간 요금: {}원", remainingFee);

    int totalFee = feeForHolidayFullDays + feeForWeekdayFullDays + remainingFee;

    DiscountType type = DiscountType.fromCode(discountType);
    log.info("type - 할인 대상: {}, 할인율: {}", type.getCode(), type.getDiscountRate());

    int finalTotalFee = totalFee * (100 - type.getDiscountRate()) / 100;

    log.info("getTotalFee - 최종 총 요금: {}원", finalTotalFee);
    log.info("=== 계산 종료 ===");
    return finalTotalFee;
  }

  private int getHolidayDayRate(String parkingLot, boolean isLargeCar) {
    if (parkingLot.equals("P1") || parkingLot.equals("P2")) {
      return isLargeCar ? LARGE_P12_HOLIDAY_24H : SMALL_P12_HOLIDAY_24H;
    } else if (parkingLot.equals("P3")) {
      return isLargeCar ? LARGE_P3_HOLIDAY_24H : SMALL_P3_HOLIDAY_24H;
    } else {
      throw new ParkingLotNotFoundException();
    }
  }

  private int getWeekdayDayRate(String parkingLot, boolean isLargeCar) {
    if (parkingLot.equals("P1") || parkingLot.equals("P2")) {
      return isLargeCar ? LARGE_P12_WEEKDAY_24H : SMALL_P12_WEEKDAY_24H;
    } else if (parkingLot.equals("P3")) {
      return isLargeCar ? LARGE_P3_WEEKDAY_24H : SMALL_P3_WEEKDAY_24H;
    } else {
      throw new ParkingLotNotFoundException();
    }
  }

  private int getRemainingFee(int minutes, boolean isLargeCar) {
    int baseCost = isLargeCar ? LARGE_BASE_COST : SMALL_BASE_COST;
    int additionalCost = isLargeCar ? LARGE_ADDITIONAL_COST : SMALL_ADDITIONAL_COST;
    log.info("getRemainingFee - 입력 남은 분: {}분, baseCost: {}원", minutes, baseCost);
    if (minutes == 0) {
      log.info("getRemainingFee - 남은 시간이 0이므로, 추가 요금 없음 (0원)");
      return 0;
    } else if (minutes <= BASE_TIME) {
      log.info("getRemainingFee - 남은 분이 기본 시간({}분) 이하이므로, 요금: {}원", BASE_TIME, baseCost);
      return baseCost;
    } else {
      int additionalMinutes = minutes - BASE_TIME;
      int additionalUnits = (int) Math.ceil((double) additionalMinutes / 10);
      int remainingFee = baseCost + additionalUnits * additionalCost;
      log.info("getRemainingFee - 추가분: {}분, 추가 단위: {}회, 추가 요금: {}원, 총 나머지 요금: {}원",
          additionalMinutes, additionalUnits, additionalUnits * additionalCost, remainingFee);
      return remainingFee;
    }
  }
}
