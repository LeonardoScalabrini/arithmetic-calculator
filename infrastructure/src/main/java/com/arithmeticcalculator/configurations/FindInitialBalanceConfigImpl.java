package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.ports.out.FindInitalBalanceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindInitialBalanceConfigImpl implements FindInitalBalanceInterface {

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
