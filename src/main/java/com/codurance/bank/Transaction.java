package com.codurance.bank;

import java.time.LocalDateTime;

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

public class Transaction {

    private final int amount;
    private final LocalDateTime timestamp;
    private final int balance;

    public Transaction(int amount, LocalDateTime timestamp, int balance) {
        this.amount = amount;
        this.timestamp = timestamp;
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        return reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    public int amount() {
        return amount;
    }

    public LocalDateTime timestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                ", balance=" + balance +
                '}';
    }
}
