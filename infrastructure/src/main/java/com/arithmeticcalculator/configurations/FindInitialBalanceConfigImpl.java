package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.usercases.interfaces.services.FindInitalBalance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindInitialBalanceConfigImpl implements FindInitalBalance {

  private final double initial;

  @Autowired
  public FindInitialBalanceConfigImpl(@Value("${initial-balance}") double initial) {
    this.initial = initial;
  }

  @Override
  public double find() {
    return initial;
  }
}
