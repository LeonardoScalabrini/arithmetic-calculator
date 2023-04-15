package com.arithmeticcalculator.configurations;

import com.arithmeticcalculator.usercases.CreateRecordUserCaseImpl;
import com.arithmeticcalculator.usercases.InitialBalanceUserCaseImpl;
import com.arithmeticcalculator.usercases.PayOperationUserCaseImpl;
import com.arithmeticcalculator.usercases.RandomStringUserCaseImpl;
import com.arithmeticcalculator.usercases.interfaces.CreateRecordUserCase;
import com.arithmeticcalculator.usercases.interfaces.InitialBalanceUserCase;
import com.arithmeticcalculator.usercases.interfaces.PayOperationUserCase;
import com.arithmeticcalculator.usercases.interfaces.RandomStringUserCase;
import com.arithmeticcalculator.usercases.interfaces.repositories.OperationRepository;
import com.arithmeticcalculator.usercases.interfaces.repositories.RecordRepository;
import com.arithmeticcalculator.usercases.interfaces.repositories.UserRepository;
import com.arithmeticcalculator.usercases.interfaces.services.FindInitalBalance;
import com.arithmeticcalculator.usercases.interfaces.services.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  private final CreateRecordUserCase createRecordUserCase;

  private final PayOperationUserCase payOperationUserCase;

  private final RandomStringUserCase randomStringUserCase;
  private final InitialBalanceUserCase initialBalanceUserCase;

  @Autowired
  public AppConfig(
      UserRepository userRepository,
      OperationRepository operationRepository,
      RecordRepository recordRepository,
      RandomString randomString,
      FindInitalBalance findInitalBalance) {
    this.createRecordUserCase = new CreateRecordUserCaseImpl(recordRepository);
    this.payOperationUserCase =
        new PayOperationUserCaseImpl(userRepository, operationRepository, createRecordUserCase);
    this.randomStringUserCase = new RandomStringUserCaseImpl(randomString);
    this.initialBalanceUserCase = new InitialBalanceUserCaseImpl(findInitalBalance, userRepository);
  }

  @Bean
  public CreateRecordUserCase getCreateRecordUserCase() {
    return createRecordUserCase;
  }

  @Bean
  public PayOperationUserCase getPayOperationUserCase() {
    return payOperationUserCase;
  }

  @Bean
  public RandomStringUserCase getRandomStringUserCase() {
    return randomStringUserCase;
  }

  @Bean
  public InitialBalanceUserCase getInitialBalanceUserCase() {
    return initialBalanceUserCase;
  }
}
