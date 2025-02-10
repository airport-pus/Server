package com.airportpus.domain.parking.presentation;

import com.airportpus.domain.parking.presentation.dto.ParkingResponse;
import com.airportpus.domain.parking.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor
public class ParkingController {

  private final ParkingService parkingService;

  @GetMapping(produces = "application/json")
  public List<ParkingResponse> getParkingInfo() {
    return parkingService.getParkingInfo();
  }
}
