package bank;

import java.time.LocalDateTime;

public class StatementLine {

    private final Transaction transaction;
    private final int balance;

    public StatementLine(Transaction transaction, int balance) {
        this.transaction = transaction;
        this.balance = balance;
    }

    public LocalDateTime timestamp() {
        return transaction.timestamp();
    }

    public int amount() {
        return transaction.amount();
    }

    public int balance() {
        return balance;
    }
}
