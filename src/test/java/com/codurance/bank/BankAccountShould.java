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
public class BankAccountShould {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    Clock clock;

    @Mock
    Statement statement;

    @InjectMocks
    private BankAccount account;

    @Test
    public void post_a_deposit() {
        int amount = 10;
        LocalDateTime timestamp = LocalDateTime.now();
        when(clock.currentTime()).thenReturn(timestamp);
        when(transactionRepository.findAllTransactions()).thenReturn(emptyList());

        account.deposit(amount);

        Transaction depositTransaction = new Transaction(amount, timestamp, amount);
        verify(transactionRepository).postTransaction(depositTransaction);
    }

    @Test
    public void post_a_withdrawal() {
        LocalDateTime timestamp = LocalDateTime.now();
        int amount = 10;
        when(clock.currentTime()).thenReturn(timestamp);
        when(transactionRepository.findAllTransactions()).thenReturn(emptyList());

        account.withdraw(amount);

        Transaction withdrawalTransaction = new Transaction(-amount, timestamp, -amount);
        verify(transactionRepository).postTransaction(withdrawalTransaction);
    }

    @Test
    public void print_a_statement() {
        List<Transaction> listOfTransactions = asList(aTransaction(), aTransaction());
        when(transactionRepository.findAllTransactions()).thenReturn(listOfTransactions);

        account.statement();

        verify(statement).print(listOfTransactions);
    }

    @Test
    public void calculate_running_balance() {
        LocalDateTime timestamp = LocalDateTime.now();
        when(clock.currentTime()).thenReturn(timestamp);
        Transaction firstTransaction = new Transaction(10, timestamp, 10);
        Transaction secondTransaction = new Transaction(-5, timestamp, 5);
        when(transactionRepository.findAllTransactions())
                .thenReturn(emptyList())
                .thenReturn(asList(firstTransaction));

        account.deposit(10);
        account.withdraw(5);

        verify(transactionRepository).postTransaction(firstTransaction);
        verify(transactionRepository).postTransaction(secondTransaction);
    }

    private Transaction aTransaction() {
        return new Transaction(1, LocalDateTime.now(), 0);
    }

}