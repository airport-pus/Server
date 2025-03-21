package com.airportpus.domain.congestion.service;

import com.airportpus.domain.congestion.domain.Congestion;
import com.airportpus.domain.congestion.domain.repository.CongestionRepository;
import com.airportpus.domain.congestion.presentation.dto.RealCongestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CongestionTransactionalService {

  private final CongestionService congestionService;
  private final CongestionRepository congestionRepository;

  public void saveCongestion() {
    RealCongestionResponse response = congestionService.getCongestionRealTime();
    Congestion congestion = Congestion.builder()
        .cgdrAllLvl(response.cgdrAllLvl())
        .cgdrALvl(response.cgdrALvl())
        .cgdrBLvl(response.cgdrBLvl())
        .cgdrCLvl(response.cgdrCLvl())
        .build();
    congestionRepository.save(congestion);
  }

  public void deleteOldCongestion() {
    LocalDateTime yesterdayEnd = LocalDate.now().minusDays(1).atTime(23, 59, 59, 999_999_999);
    congestionRepository.deleteByCreatedAtBefore(yesterdayEnd);
  }
}

