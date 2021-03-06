package com.codurance.bank;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private List<Transaction> transactions = new ArrayList<>();

    public void postTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> findAllTransactions() {
        return transactions;
    }
}
