package com.airportpus.domain.congestion.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CongestionSchedulerService {

  private final CongestionTransactionalService congestionTransactionalService;

  @Scheduled(cron = "0 0 * * * *", zone = "Asia/Seoul")
  public void saveCongestion() {
    log.info("start saveCongestion");
    congestionTransactionalService.saveCongestion();
    log.info("end saveCongestion");
  }

//  @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
  public void deleteOldCongestion() {
    log.info("start deleteOldCongestion");
    congestionTransactionalService.deleteOldCongestion();
    log.info("end deleteOldCongestion");
  }
}
