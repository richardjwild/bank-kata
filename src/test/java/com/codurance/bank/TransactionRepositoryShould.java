package com.codurance.bank;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.fest.assertions.Assertions.assertThat;

public class TransactionRepositoryShould {

    private TransactionRepository transactionRepository = new TransactionRepository();

    @Test
    public void be_initially_empty() {
        assertThat(transactionRepository.findAllTransactions()).isEmpty();
    }

    @Test
    public void return_all_posted_transactions() {
        Transaction t1 = new Transaction(1, LocalDateTime.now());
        Transaction t2 = new Transaction(2, LocalDateTime.now().plusSeconds(1));

        transactionRepository.postTransaction(t1);
        transactionRepository.postTransaction(t2);

        assertThat(transactionRepository.findAllTransactions()).containsExactly(t1, t2);
    }

}