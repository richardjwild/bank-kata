package com.codurance.bank;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.fest.assertions.Assertions.assertThat;

public class TransactionFormatterShould {

    private TransactionFormatter transactionFormatter = new TransactionFormatter();

    @Test
    public void format_a_deposit_transaction() {
        Transaction transaction = new Transaction(1000, LocalDateTime.of(2012, 1, 14, 0, 0), 1500);

        String result = transactionFormatter.format(transaction);

        assertThat(result).isEqualTo("14/01/2012 || 1000.00 || || 1500.00");
    }
}