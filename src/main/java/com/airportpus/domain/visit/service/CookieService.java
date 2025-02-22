package com.airportpus.domain.visit.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CookieService {

  private static final String VISITOR_COOKIE = "visited";
  private static final int COOKIE_EXPIRATION = 60 * 60;
  private static final String DOMAIN = "airport-pus.kr";

  public boolean hasVisitCookie(HttpServletRequest request) {
    if (request.getCookies() != null) {
      for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
        if (VISITOR_COOKIE.equals(cookie.getName())) {
          log.info("방문 쿠키 발견: {}", cookie.getName());
          return true;
        }
      }
    }
    log.info("방문 쿠키 없음.");
    return false;
  }

  public void createVisitCookie(HttpServletResponse response) {
    ResponseCookie cookie = ResponseCookie.from(VISITOR_COOKIE, "true")
        .domain(DOMAIN)
        .maxAge(COOKIE_EXPIRATION)
        .path("/")
        .secure(true)
        .httpOnly(true)
        .sameSite("None")
        .build();

    response.addHeader("Set-Cookie", cookie.toString());
    log.info("방문 쿠키 생성: {}", cookie.getName());
  }
}
