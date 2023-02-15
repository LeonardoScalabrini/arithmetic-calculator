package com.arithmeticcalculator.usercases.interfaces.services;

import com.arithmeticcalculator.domains.exceptions.OperationException;

public interface RandomString {
  String random() throws OperationException;
}
