package com.arithmeticcalculator.externals;

import com.arithmeticcalculator.configurations.interfaces.RandomOrgConfig;
import com.arithmeticcalculator.domains.interfaces.RandomService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RandomOrgServiceImpl implements RandomService {
  private final RestTemplate restTemplate;
  private static final String STRING_GENERATOR_PATH =
      "/strings/?num=1&len=20&digits=on&upperalpha=on&loweralpha=on&unique=on&format=plain&rnd=new";
  private final RandomOrgConfig randomOrgConfig;

  @Autowired
  public RandomOrgServiceImpl(
      RestTemplateBuilder restTemplateBuilder, RandomOrgConfig randomOrgConfig) {
    this.restTemplate = restTemplateBuilder.build();
    this.randomOrgConfig = randomOrgConfig;
  }

  public Optional<String> stringGenerator() {
    var result =
        restTemplate.getForEntity(
            String.format("%s/%s", randomOrgConfig.getHost(), STRING_GENERATOR_PATH), String.class);
    if (result.getStatusCode().is2xxSuccessful() && result.hasBody())
      return Optional.ofNullable(result.getBody());
    return Optional.empty();
  }
}
