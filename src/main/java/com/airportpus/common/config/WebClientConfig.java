package com.airportpus.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.codec.xml.Jaxb2XmlEncoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

  private final ApiProperties apiProperties;

  @Bean
  @Qualifier("parkingWebClient")
  public WebClient parkingWebClient() {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(apiProperties.getParkingBaseUrl());
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

    return WebClient.builder()
        .uriBuilderFactory(factory)
        .exchangeStrategies(ExchangeStrategies.builder()
            .codecs(clientCodecConfigurer -> {
              clientCodecConfigurer.defaultCodecs().jaxb2Decoder(new Jaxb2XmlDecoder());
              clientCodecConfigurer.defaultCodecs().jaxb2Encoder(new Jaxb2XmlEncoder());
            })
            .build())
        .build();
  }

  @Bean
  @Qualifier("congestionWebClient")
  public WebClient congestionWebClient() {
    DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(apiProperties.getCongestionBaseUrl());
    factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

    return WebClient.builder()
        .uriBuilderFactory(factory)
        .build();
  }
}
