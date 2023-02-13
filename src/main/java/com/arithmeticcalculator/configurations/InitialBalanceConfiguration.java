package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.domains.interfaces.FindInitalBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialBalanceConfiguration implements FindInitalBalance {

  private final double initial;

  @Autowired
  public InitialBalanceConfiguration(@Value("${initial-balance}") double initial) {
    this.initial = initial;
  }

  @Override
  public double find() {
    return initial;
  }
}
