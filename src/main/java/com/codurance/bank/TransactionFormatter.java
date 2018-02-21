package com.codurance.bank;

import java.time.format.DateTimeFormatter;

public class TransactionFormatter {

    public String format(Transaction transaction) {
        if (transaction.amount() > 0) {
            return String.format("%s || %.2f || || %.2f",
                    transaction.timestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    (float) transaction.amount(),
                    (float) transaction.balance());
        } else {
            return String.format("%s || || %.2f || %.2f",
                    transaction.timestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    (float) transaction.amount() * -1,
                    (float) transaction.balance());
        }
    }
}
