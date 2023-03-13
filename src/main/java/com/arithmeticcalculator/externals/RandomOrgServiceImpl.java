package com.arithmeticcalculator.externals;

import static com.arithmeticcalculator.domains.exceptions.IllegalStateExceptionFactory.getInstance;

import com.arithmeticcalculator.configurations.interfaces.RandomOrgConfig;
import com.arithmeticcalculator.externals.interfaces.RandomOrgService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public final class RandomOrgServiceImpl implements RandomOrgService {
  private final RestTemplate restTemplate;
  private final RandomOrgConfig randomOrgConfig;

  @Autowired
  public RandomOrgServiceImpl(
      @NonNull RestTemplateBuilder restTemplateBuilder, @NonNull RandomOrgConfig randomOrgConfig) {
    this.restTemplate = restTemplateBuilder.build();
    restTemplate.setErrorHandler(NoResponseErrorHandlerImpl.getInstance());
    this.randomOrgConfig = randomOrgConfig;
  }

  @Override
  public String strings() {
    var result = restTemplate.getForEntity(randomOrgConfig.getUrl(), String.class);
    if (result.getStatusCode().is2xxSuccessful() && result.hasBody()) return result.getBody();
    throw getInstance()
        .param("url", randomOrgConfig.getUrl())
        .param("status", result.getStatusCode())
        .build();
  }
}
