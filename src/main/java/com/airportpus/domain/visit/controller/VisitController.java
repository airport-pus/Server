package com.airportpus.domain.visit.controller;

import com.airportpus.domain.visit.service.CookieService;
import com.airportpus.domain.visit.service.VisitService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/visit")
@RequiredArgsConstructor
public class VisitController {

  private final VisitService visitorService;
  private final CookieService cookieService;

  @GetMapping(produces = "application/json")
  public long getVisit() {
    return visitorService.getTotalVisitCount();
  }

  @PostMapping(produces = "application/json")
  public ResponseEntity<String> checkVisit(HttpServletRequest request, HttpServletResponse response) {
    if (!cookieService.hasVisitCookie(request)) {
      visitorService.incrementVisitCount();
      cookieService.createVisitCookie(response);
      return ResponseEntity.ok("첫 방문");
    }

    return ResponseEntity.ok("방문기록 존재");
  }
}
