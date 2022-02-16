package com.codurance.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatementShould {

    private static final List<Transaction> NO_TRANSACTIONS = emptyList();

    @Mock
    private Output output;

    @Mock
    private Transaction transaction, earlierTransaction;

    @InjectMocks
    private Statement statement;

    @Test
    public void print_an_empty_statement() {
        statement.print(NO_TRANSACTIONS);

        verify(output).print("date || credit || debit || balance");
    }

    @Test
    public void print_a_formatted_transaction() {
        when(transaction.timestamp()).thenReturn(LocalDateTime.of(2022, 02, 16, 0, 0));
        when(transaction.amount()).thenReturn(100);

        statement.print(asList(transaction));

        verify(output).print(
                "date || credit || debit || balance\n" +
                "16/02/2022 || 100.00 || || 100.00");
    }

    @Test
    public void print_transactions_in_reverse_date_order() {
        var timestamp = LocalDateTime.of(2022, 02, 16, 0, 0);
        when(transaction.timestamp()).thenReturn(timestamp);
        when(transaction.amount()).thenReturn(-10);
        when(earlierTransaction.timestamp()).thenReturn(timestamp.minusDays(1));
        when(earlierTransaction.amount()).thenReturn(100);

        statement.print(asList(earlierTransaction, transaction));

        verify(output).print(
                "date || credit || debit || balance\n" +
                "16/02/2022 || || 10.00 || 90.00\n" +
                "15/02/2022 || 100.00 || || 100.00");
    }
}