package com.codurance.bank;

public class BankAccount {

    private final TransactionRepository transactionRepository;
    private final Clock clock;
    private final Statement statement;

    public BankAccount(TransactionRepository transactionRepository, Clock clock, Statement statement) {
        this.transactionRepository = transactionRepository;
        this.clock = clock;
        this.statement = statement;
    }

    public void deposit(int amount) {
        postTransaction(amount);
    }

    public void withdraw(int amount) {
        postTransaction(-amount);
    }

    private void postTransaction(int transactionAmount) {
        int balance = transactionRepository.findAllTransactions().stream()
                .mapToInt(Transaction::amount)
                .sum();

        Transaction transaction = new Transaction(
                transactionAmount,
                clock.currentTime(), balance + transactionAmount);
        transactionRepository.postTransaction(transaction);
    }

    public void statement() {
        statement.print(transactionRepository.findAllTransactions());
    }
}
