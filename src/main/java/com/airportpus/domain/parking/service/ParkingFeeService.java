package com.airportpus.domain.parking.service;

import com.airportpus.domain.parking.exception.ParkingLotNotFoundException;
import com.airportpus.domain.parking.presentation.dto.value.DiscountType;
import org.springframework.stereotype.Service;

import static com.airportpus.domain.parking.service.dto.ParkingFeeResponse.*;

@Service
public class ParkingFeeService {

  public int getTotalFee(int holidayMinutes, int weekdayMinutes, String parkingLot, boolean isLargeCar, int discountType) {
    if (holidayMinutes + weekdayMinutes <= 20) return 0;

    int holidayFullDays = holidayMinutes / 1440;
    int weekdayFullDays = weekdayMinutes / 1440;

    int holidayDayRate = getHolidayDayRate(parkingLot, isLargeCar);
    int weekdayDayRate = getWeekdayDayRate(parkingLot, isLargeCar);

    int feeForHolidayFullDays = holidayFullDays * holidayDayRate;
    int feeForWeekdayFullDays = weekdayFullDays * weekdayDayRate;

    int holidayRemaining = holidayMinutes % 1440;
    int weekdayRemaining = weekdayMinutes % 1440;
    int combinedRemaining = holidayRemaining + weekdayRemaining;

    int remainingFee = getRemainingFee(combinedRemaining, isLargeCar);
    int totalFee = feeForHolidayFullDays + feeForWeekdayFullDays + remainingFee;

    DiscountType type = DiscountType.fromCode(discountType);
    return totalFee * (100 - type.getDiscountRate()) / 100;
  }

  private int getHolidayDayRate(String parkingLot, boolean isLargeCar) {
    if (parkingLot.equals("P1") || parkingLot.equals("P2")) return isLargeCar ? LARGE_P12_HOLIDAY_24H : SMALL_P12_HOLIDAY_24H;
    else if (parkingLot.equals("P3")) return isLargeCar ? LARGE_P3_HOLIDAY_24H : SMALL_P3_HOLIDAY_24H;
    else throw new ParkingLotNotFoundException();
  }

  private int getWeekdayDayRate(String parkingLot, boolean isLargeCar) {
    if (parkingLot.equals("P1") || parkingLot.equals("P2")) return isLargeCar ? LARGE_P12_WEEKDAY_24H : SMALL_P12_WEEKDAY_24H;
    else if (parkingLot.equals("P3")) return isLargeCar ? LARGE_P3_WEEKDAY_24H : SMALL_P3_WEEKDAY_24H;
    else throw new ParkingLotNotFoundException();
  }

  private int getRemainingFee(int minutes, boolean isLargeCar) {
    int baseCost = isLargeCar ? LARGE_BASE_COST : SMALL_BASE_COST;
    int additionalCost = isLargeCar ? LARGE_ADDITIONAL_COST : SMALL_ADDITIONAL_COST;

    if (minutes == 0) return 0;
    else if (minutes <= BASE_TIME) return baseCost;
    else {
      int additionalMinutes = minutes - BASE_TIME;
      int additionalUnits = (int) Math.ceil((double) additionalMinutes / 10);
      return baseCost + additionalUnits * additionalCost;
    }
  }
}
