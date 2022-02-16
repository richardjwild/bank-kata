package com.codurance.bank;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.fest.assertions.Assertions.assertThat;

public class StatementFormatterShould {

    private StatementFormatter statementFormatter = new StatementFormatter();

    @Test
    public void format_a_deposit_transaction() {
        var statementLine = new StatementLine(new Transaction(1000, LocalDateTime.of(2012, 1, 14, 0, 0)), 1500);

        var result = statementFormatter.format(statementLine);

        assertThat(result).isEqualTo("14/01/2012 || 1000.00 || || 1500.00");
    }

    @Test
    public void format_a_withdrawal_transaction() {
        var transaction = new StatementLine(new Transaction(-1000, LocalDateTime.of(2012, 1, 14, 0, 0)), 1500);

        var result = statementFormatter.format(transaction);

        assertThat(result).isEqualTo("14/01/2012 || || 1000.00 || 1500.00");
    }
}