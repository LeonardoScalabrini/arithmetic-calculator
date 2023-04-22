package com.arithmeticcalculator.domains.specifications;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.User;
import java.util.function.BiPredicate;

public interface PaymentSpecification extends BiPredicate<User, CostOperation> {}
