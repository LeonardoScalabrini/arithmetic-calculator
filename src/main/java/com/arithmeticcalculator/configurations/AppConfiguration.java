package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.domains.commands.StringGeneratorCommand;
import com.arithmeticcalculator.domains.interfaces.StringGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
  private final StringGeneratorService stringGeneratorService;

  @Autowired
  public AppConfiguration(StringGeneratorService stringGeneratorService) {
    this.stringGeneratorService = stringGeneratorService;
  }

  @Bean
  public StringGeneratorCommand stringGeneratorCommand() {
    return new StringGeneratorCommand(stringGeneratorService);
  }
}
