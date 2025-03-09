package com.airportpus.domain.congestion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CongestionSchedulerService {

  private final CongestionTransactionalService congestionTransactionalService;

  @Scheduled(cron = "0 0 * * * *", zone = "Asia/Seoul")
  public void saveCongestion() {
    congestionTransactionalService.saveCongestion();
  }

  @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
  public void deleteOldCongestionData() {
    congestionTransactionalService.deleteOldCongestion();
  }
}
