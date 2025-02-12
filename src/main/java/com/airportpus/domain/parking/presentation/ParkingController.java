package com.airportpus.domain.parking.presentation;

import com.airportpus.domain.parking.presentation.dto.ParkingFeeRequest;
import com.airportpus.domain.parking.presentation.dto.ParkingResponse;
import com.airportpus.domain.parking.service.ParkingFeeService;
import com.airportpus.domain.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

  private final ParkingService parkingService;
  private final ParkingFeeService parkingFeeService;

  @GetMapping(produces = "application/json")
  public List<ParkingResponse> getParkingByRealTime() {
    return parkingService.getParkingByRealTime();
  }

  @PostMapping(produces = "application/json")
  public int getParkingFee(@RequestBody ParkingFeeRequest request) {
    return parkingFeeService.getTotalFee(
        request.holidayMinutes(),
        request.weekdayMinutes(),
        request.parkingLot(),
        request.isLargeCar(),
        request.discountType()
    );
  }
}
