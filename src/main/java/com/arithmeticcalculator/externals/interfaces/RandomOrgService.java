package com.arithmeticcalculator.externals.interfaces;

import java.util.Optional;
import org.springframework.web.client.HttpStatusCodeException;

public interface RandomOrgService {
  Optional<String> stringGenerator() throws HttpStatusCodeException;
}
