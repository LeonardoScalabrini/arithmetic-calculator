package com.arithmeticcalculator.domains.interfaces;

import com.arithmeticcalculator.domains.Record;

public interface RecordRepository {
    <T> void save(Record<T> record);
}
