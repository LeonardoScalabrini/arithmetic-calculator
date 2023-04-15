package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.configurations.interfaces.RandomOrgConfig;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RandomOrgConfigImpl implements RandomOrgConfig {

  private final String host;
  private final String strings;
  private final StringBuilder url;

  @Autowired
  public RandomOrgConfigImpl(
      @Value("${random-org-host}") @NonNull String host,
      @Value("${random-org-strings}") @NonNull String strings) {
    this.host = host;
    this.strings = strings;
    this.url = new StringBuilder().append(host).append(strings);
  }

  @Override
  public String getHost() {
    return host;
  }

  @Override
  public String getStrings() {
    return strings;
  }

  @Override
  public String getUrl() {
    return url.toString();
  }
}
