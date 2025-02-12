package com.airportpus.domain.parking.service;

import com.airportpus.domain.parking.domain.Parking;
import com.airportpus.domain.parking.domain.repository.ParkingRepository;
import com.airportpus.domain.parking.presentation.dto.ParkingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ParkingSchedulerService {

  private final ParkingService parkingService;
  private final ParkingRepository parkingRepository;

  @Scheduled(cron = "0 0 * * * *")
  public void saveParking() {
    List<ParkingResponse> parkingInfoList = parkingService.getParkingByRealTime();
    parkingInfoList.forEach(parkingResponse -> {
      Parking parking = Parking.builder()
          .airportCodeName(parkingResponse.parkingAirportCodeName())
          .congestion(parkingResponse.parkingCongestion())
          .congestionDegree(parkingResponse.parkingCongestionDegree())
          .totalSpace(parkingResponse.parkingTotalSpace())
          .remainingSpace(parkingResponse.remainingSpace())
          .occupiedSpace(parkingResponse.parkingOccupiedSpace())
          .build();
      parkingRepository.save(parking);
    });
  }
}
