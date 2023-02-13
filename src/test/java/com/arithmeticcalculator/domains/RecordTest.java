package com.arithmeticcalculator.domains;

import com.arithmeticcalculator.fixtures.Fixture;
import com.jparams.verifier.tostring.ToStringVerifier;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecordTest {
    @Test
    void build() {
        var record = Fixture.getRecord();
        assertNotNull(record.getDate());
        assertEquals(5, record.getAmount());
        assertEquals(20, record.getBalance());
        assertEquals(Fixture.getOperation(), record.getOperation());
        assertEquals(Fixture.getUser(), record.getUser());
        assertEquals(2.0, record.getOperationResult());
        EqualsVerifier.simple().forClass(Record.class).suppress(Warning.STRICT_INHERITANCE).verify();
        ToStringVerifier.forClass(Record.class).verify();
    }

    @Test
    void notNull() {
        assertThrows(NullPointerException.class, () -> Record.<Double>builder().build());
    }
}