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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StatementShould {

    private static final List<Transaction> NO_TRANSACTIONS = emptyList();

    @Mock
    private TransactionFormatter transactionFormatter;

    @Mock
    private Output output;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private Statement statement;

    @Test
    public void print_an_empty_statement() {
        statement.print(NO_TRANSACTIONS);

        verify(transactionFormatter, never()).format(any());
        verify(output).print("date || credit || debit || balance");
    }

    @Test
    public void print_a_formatted_transaction() {
        when(transactionFormatter.format(transaction)).thenReturn("a formatted transaction");

        statement.print(asList(transaction));
        verify(output).print(
                "date || credit || debit || balance\n" +
                "a formatted transaction");
    }

}