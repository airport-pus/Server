package com.airportpus.domain.visit.service;

import jakarta.servlet.http.Cookie;
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

  public boolean hasVisitCookie(HttpServletRequest request) {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
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
    ResponseCookie cookie = ResponseCookie.from("visited", "true")
        .maxAge(COOKIE_EXPIRATION)
        .path("/")
        .httpOnly(true)
        .secure(true)
        .sameSite("None")
        .build();

    response.addHeader("Set-Cookie", cookie.toString());
    log.info("쿠키 헤더에 추가");
  }
}
