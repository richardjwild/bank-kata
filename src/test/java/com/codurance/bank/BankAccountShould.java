package com.codurance.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankAccountShould {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    Clock clock;

    @Test
    public void post_a_deposit() {
        int amount = 10;
        LocalDateTime timestamp = LocalDateTime.now();
        when(clock.currentTime()).thenReturn(timestamp);

        BankAccount account = new BankAccount(transactionRepository, clock);
        account.deposit(amount);

        Transaction depositTransaction = new Transaction(amount, timestamp);
        verify(transactionRepository).postTransaction(depositTransaction);
    }

}