package com.arithmeticcalculator.ioc;

import com.arithmeticcalculator.domains.specifications.PaymentSpecificationImpl;
import com.arithmeticcalculator.ports.in.*;
import com.arithmeticcalculator.ports.out.*;
import com.arithmeticcalculator.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  private final CreateRecordService createRecordService;
  private final PayCostOperationService payCostOperationService;
  private final RandomStringService randomStringService;
  private final CreateUserService createUserService;

  @Autowired
  public AppConfig(
      UserRepositoryInterface userRepository,
      CostOperationRepositoryInterface operationRepository,
      RecordRepositoryInterface recordRepository,
      RandomStringInterface randomString,
      FindInitalBalanceInterface findInitalBalance) {
    this.createRecordService = CreateRecordServiceImpl.newInstance(recordRepository);
    this.createUserService = CreateUserServiceImpl.newInstance(userRepository, findInitalBalance);
    this.payCostOperationService =
        PayCostOperationServiceImpl.newInstance(
            userRepository,
            operationRepository,
            createRecordService,
            PaymentSpecificationImpl.getInstance());
    this.randomStringService = RandomStringServiceImpl.newInstance(randomString);
  }

  @Bean
  public CreateRecordService getCreateRecordService() {
    return createRecordService;
  }

  @Bean
  public PayCostOperationService getPayOperationService() {
    return payCostOperationService;
  }

  @Bean
  public RandomStringService getRandomStringService() {
    return randomStringService;
  }

  @Bean
  public CreateUserService getCreateUserService() {
    return createUserService;
  }
}
