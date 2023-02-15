package com.arithmeticcalculator.externals;

import com.arithmeticcalculator.configurations.interfaces.RandomOrgConfig;
import com.arithmeticcalculator.externals.exceptions.RandomOrgException;
import com.arithmeticcalculator.externals.interfaces.RandomOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomOrgServiceImpl implements RandomOrgService {
  private final RestTemplate restTemplate;
  private final RandomOrgConfig randomOrgConfig;

  @Autowired
  public RandomOrgServiceImpl(
      RestTemplateBuilder restTemplateBuilder, RandomOrgConfig randomOrgConfig) {
    this.restTemplate = restTemplateBuilder.build();
    restTemplate.setErrorHandler(NoResponseErrorHandlerImpl.getInstance());
    this.randomOrgConfig = randomOrgConfig;
  }

  @Override
  public String strings() throws RandomOrgException {
    var result =
        restTemplate.getForEntity(
            String.format("%s/%s", randomOrgConfig.getHost(), randomOrgConfig.getStrings()),
            String.class);
    if (result.getStatusCode().is2xxSuccessful() && result.hasBody()) return result.getBody();
    throw new RandomOrgException();
  }
}
