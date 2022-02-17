package com.codurance.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountShould {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    Clock clock;

    @Mock
    StatementPrinter statement;

    @InjectMocks
    private BankAccount account;

    @Test
    public void post_a_deposit() {
        var amount = 10;
        var timestamp = LocalDateTime.now();
        when(clock.currentTime()).thenReturn(timestamp);
        when(transactionRepository.findAllTransactions()).thenReturn(emptyList());

        account.deposit(amount);

        var depositTransaction = new Transaction(amount, timestamp);
        verify(transactionRepository).add(depositTransaction);
    }

    @Test
    public void post_a_withdrawal() {
        var timestamp = LocalDateTime.now();
        var amount = 10;
        when(clock.currentTime()).thenReturn(timestamp);
        when(transactionRepository.findAllTransactions()).thenReturn(emptyList());

        account.withdraw(amount);

        var withdrawalTransaction = new Transaction(-amount, timestamp);
        verify(transactionRepository).add(withdrawalTransaction);
    }

    @Test
    public void print_a_statement() {
        var listOfTransactions = asList(aTransaction(), aTransaction());
        when(transactionRepository.findAllTransactions()).thenReturn(listOfTransactions);

        account.statement();

        verify(statement).print(listOfTransactions);
    }

    @Test
    public void calculate_running_balance() {
        var timestamp = LocalDateTime.now();
        when(clock.currentTime()).thenReturn(timestamp);
        var firstTransaction = new Transaction(10, timestamp);
        var secondTransaction = new Transaction(-5, timestamp);
        when(transactionRepository.findAllTransactions())
                .thenReturn(emptyList())
                .thenReturn(asList(firstTransaction));

        account.deposit(10);
        account.withdraw(5);

        verify(transactionRepository).add(firstTransaction);
        verify(transactionRepository).add(secondTransaction);
    }

    private Transaction aTransaction() {
        return new Transaction(1, LocalDateTime.now());
    }

}