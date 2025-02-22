package com.airportpus.domain.visit.controller;

import com.airportpus.domain.visit.service.CookieService;
import com.airportpus.domain.visit.service.VisitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Visit API", description = "방문자 API")
@Slf4j
@RestController
@RequestMapping("/visit")
@RequiredArgsConstructor
public class VisitController {

  private final VisitService visitorService;
  private final CookieService cookieService;

  @Operation(summary = "총 방문자 수 조회", description = "총 방문자 수를 조회합니다.")
  @GetMapping(produces = "application/json")
  public long getVisit() {
    long totalVisit = visitorService.getTotalVisitCount();
    log.info("총 방문자 수 조회: {}", totalVisit);
    return totalVisit;
  }

  @Operation(summary = "방문 여부 체크", description = "첫 방문이면 방문자를 증가시킵니다.")
  @PostMapping(produces = "application/json")
  public ResponseEntity<String> checkVisit(HttpServletRequest request, HttpServletResponse response) {
    if (!cookieService.hasVisitCookie(request)) {
      log.info("첫 방문으로 판단하여 방문자 수 증가 및 쿠키 생성 처리 시작.");
      visitorService.incrementVisitCount();
      cookieService.createVisitCookie(response);
      return ResponseEntity.ok("첫 방문");
    }
    log.info("이미 방문 기록이 존재합니다.");
    return ResponseEntity.ok("방문기록 존재");
  }
}
