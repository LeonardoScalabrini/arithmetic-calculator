package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.ports.in.CreateRecordService;
import com.arithmeticcalculator.ports.in.InitialBalanceService;
import com.arithmeticcalculator.ports.in.PayOperationService;
import com.arithmeticcalculator.ports.in.RandomStringService;
import com.arithmeticcalculator.ports.out.*;
import com.arithmeticcalculator.services.CreateRecordServiceImpl;
import com.arithmeticcalculator.services.InitialBalanceServiceImpl;
import com.arithmeticcalculator.services.PayCostOperationServiceImpl;
import com.arithmeticcalculator.services.RandomStringServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  private final CreateRecordService createRecordService;

  private final PayOperationService payOperationService;

  private final RandomStringService randomStringService;
  private final InitialBalanceService initialBalanceService;

  @Autowired
  public AppConfig(
      UserRepositoryInterface userRepository,
      CostOperationRepositoryInterface operationRepository,
      RecordRepositoryInterface recordRepository,
      RandomStringInterface randomString,
      FindInitalBalanceInterface findInitalBalance) {
    this.createRecordService = CreateRecordServiceImpl.newInstance(recordRepository);
    this.payOperationService =
        PayCostOperationServiceImpl.newInstance(
            userRepository, operationRepository, createRecordService);
    this.randomStringService = RandomStringServiceImpl.newInstance(randomString);
    this.initialBalanceService =
        InitialBalanceServiceImpl.newInstance(findInitalBalance, userRepository);
  }

  @Bean
  public CreateRecordService getCreateRecordService() {
    return createRecordService;
  }

  @Bean
  public PayOperationService getPayOperationService() {
    return payOperationService;
  }

  @Bean
  public RandomStringService getRandomStringService() {
    return randomStringService;
  }

  @Bean
  public InitialBalanceService getInitialBalanceService() {
    return initialBalanceService;
  }
}
