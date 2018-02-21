package com.codurance.bank;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Transaction {

    private final int amount;
    private final LocalDateTime timestamp;

    public Transaction(int amount, LocalDateTime timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }
}
