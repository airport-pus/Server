package com.airportpus.domain.visit.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

  private static final String VISITOR_COOKIE = "visited";
  private static final int COOKIE_EXPIRATION = 60 * 60;

  public boolean hasVisitCookie(HttpServletRequest request) {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (VISITOR_COOKIE.equals(cookie.getName())) {
          return true;
        }
      }
    }
    return false;
  }

  public void createVisitCookie(HttpServletResponse response) {
    Cookie cookie = new Cookie(VISITOR_COOKIE, "true");
    cookie.setMaxAge(COOKIE_EXPIRATION);
    cookie.setPath("/");
    cookie.setHttpOnly(true);
    response.addCookie(cookie);
  }
}



