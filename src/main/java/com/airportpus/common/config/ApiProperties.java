package com.airportpus.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiProperties {

  private String serviceKey;
  private String parkingBaseUrl;
  private String congestionBaseUrl;
  private String apronBaseUrl;
}
