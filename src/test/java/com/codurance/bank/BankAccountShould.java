package com.codurance.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.longThat;
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

        account.deposit(amount);

        Transaction depositTransaction = new Transaction(amount, timestamp);
        verify(transactionRepository).postTransaction(depositTransaction);
    }

    @Test
    public void post_a_withdrawal() {
        LocalDateTime timestamp = LocalDateTime.now();
        int amount = 10;
        when(clock.currentTime()).thenReturn(timestamp);

        account.withdraw(amount);

        Transaction withdrawalTransaction = new Transaction(-1 * amount, timestamp);
        verify(transactionRepository).postTransaction(withdrawalTransaction);
    }

    @Test
    public void print_a_statement() {
        List<Transaction> listOfTransactions = asList(aTransaction(), aTransaction());
        when(transactionRepository.findAllTransactions()).thenReturn(listOfTransactions);

        account.statement();

        verify(statement).print(listOfTransactions);
    }

    private Transaction aTransaction() {
        return new Transaction(1, LocalDateTime.now());
    }

}