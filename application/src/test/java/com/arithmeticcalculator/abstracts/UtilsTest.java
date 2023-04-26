package com.arithmeticcalculator.abstracts;

import static com.arithmeticcalculator.fixtures.Fixture.*;

import com.arithmeticcalculator.domains.CostOperation;
import com.arithmeticcalculator.domains.Password;
import com.arithmeticcalculator.domains.User;
import com.arithmeticcalculator.domains.ids.CostOperationId;
import com.arithmeticcalculator.domains.ids.UserId;
import com.google.common.testing.NullPointerTester;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public abstract class UtilsTest {
  protected <T> void assertClass(Class<T> tClass, T instance) {
    var test =
        new NullPointerTester()
            .setDefault(CostOperationId.class, CostOperationId.newInstance())
            .setDefault(CostOperation.class, getCostOperation())
            .setDefault(User.class, getUser())
            .setDefault(Password.class, Password.newInstance("password"))
            .setDefault(UserId.class, UserId.newInstance());
    test.testAllPublicStaticMethods(tClass);
    test.testAllPublicInstanceMethods(instance);
    test.testAllPublicConstructors(tClass);
    EqualsVerifier.forClass(tClass).suppress(Warning.STRICT_INHERITANCE).verify();
    ToStringVerifier.forClass(tClass).verify();
  }
}
