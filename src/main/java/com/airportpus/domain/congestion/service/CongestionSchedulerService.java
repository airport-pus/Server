package com.airportpus.domain.congestion.service;

import com.airportpus.domain.congestion.domain.Congestion;
import com.airportpus.domain.congestion.domain.repository.CongestionRepository;
import com.airportpus.domain.congestion.presentation.dto.RealCongestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class CongestionSchedulerService {

  private final CongestionService congestionService;
  private final CongestionRepository congestionRepository;

  @Scheduled(cron = "0 0 * * * *")
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

  @Scheduled(cron = "0 0 0 * * *")
  public void deleteOldCongestionData() {
    LocalDateTime cutoff = LocalDateTime.now().minusDays(1);
    congestionRepository.deleteByCreatedAtBefore(cutoff);
  }
}
