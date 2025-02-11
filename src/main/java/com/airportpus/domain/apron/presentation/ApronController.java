package com.airportpus.domain.apron.presentation;

import com.airportpus.domain.apron.service.ApronService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apron")
@RequiredArgsConstructor
public class ApronController {

  private final ApronService apronService;

  @GetMapping(produces = "application/json")
  public List<?> getInApron(@RequestParam("io") String type) {
    return apronService.getApronInfo(type);
  }
}
