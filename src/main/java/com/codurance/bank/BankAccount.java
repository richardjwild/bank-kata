package com.codurance.bank;

public class BankAccount {

    private final TransactionRepository transactionRepository;
    private final Clock clock;

    public BankAccount(TransactionRepository transactionRepository, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
    }

    public void deposit(int amount) {
        transactionRepository.postTransaction(new Transaction(amount, clock.currentTime()));
    }

    public void withdraw(int amount) {

    }

    public void statement() {
    }
}
