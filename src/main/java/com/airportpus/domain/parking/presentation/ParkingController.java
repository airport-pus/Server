package com.airportpus.domain.parking.presentation;

import com.airportpus.domain.parking.presentation.dto.ParkingFeeRequest;
import com.airportpus.domain.parking.presentation.dto.ParkingResponse;
import com.airportpus.domain.parking.service.ParkingFeeService;
import com.airportpus.domain.parking.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Parking API", description = "주차장 API")
@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

  private final ParkingService parkingService;
  private final ParkingFeeService parkingFeeService;

  @Operation(summary = "공항 주차장 정보 조회", description = "실시간 주차장 정보를 조회합니다.")
  @GetMapping(produces = "application/json")
  public List<ParkingResponse> getParking() {
    return parkingService.getParking();
  }

  @Operation(summary = "주차 요금 계산", description = "입력된 정보를 바탕으로 주차 요금을 계산합니다.")
  @PostMapping(produces = "application/json")
  public int getParkingFee(
      @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "주차 요금 요청 데이터", required = true)
      @RequestBody ParkingFeeRequest request) {
    return parkingFeeService.getTotalFee(
        request.holidayMinutes(),
        request.weekdayMinutes(),
        request.parkingLot(),
        request.isLargeCar(),
        request.discountType()
    );
  }
}
