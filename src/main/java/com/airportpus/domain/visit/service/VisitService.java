package com.airportpus.domain.visit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {

  private final StringRedisTemplate redisTemplate;
  private static final String TOTAL_VISIT_KEY = "totalVisit";

  public void incrementVisitCount() {
    redisTemplate.opsForValue().increment(TOTAL_VISIT_KEY);
  }

  public long getTotalVisitCount() {
    String count = redisTemplate.opsForValue().get(TOTAL_VISIT_KEY);
    return Long.parseLong(count);
  }
}
