package com.codurance.bank;

public class BankAccount {

    private final TransactionRepository transactionRepository;
    private final Clock clock;

    public BankAccount(TransactionRepository transactionRepository, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
    }

    public void deposit(int amount) {
        postTransaction(amount);
    }

    public void withdraw(int amount) {
        postTransaction(-amount);
    }

    private void postTransaction(int transactionAmount) {
        Transaction transaction = new Transaction(
                transactionAmount,
                clock.currentTime());
        transactionRepository.postTransaction(transaction);
    }

    public void statement() {
    }
}
