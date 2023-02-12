package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.configurations.interfaces.RandomOrgConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomOrgConfigImpl implements RandomOrgConfig {

  private final String host;

  @Autowired
  public RandomOrgConfigImpl(@Value("${random-org-host}") String host) {
    this.host = host;
  }

  @Override
  public String getHost() {
    return host;
  }
}
