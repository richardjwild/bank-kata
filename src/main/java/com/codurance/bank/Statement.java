package com.codurance.bank;

import java.util.Comparator;
import java.util.List;

public class Statement {

    private Output output;
    private final TransactionFormatter formatter;

    public Statement(Output output, TransactionFormatter formatter) {
        this.output = output;
        this.formatter = formatter;
    }

    public void print(List<Transaction> listOfTransactions) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("date || credit || debit || balance");


        listOfTransactions.stream()
                .sorted(Comparator.comparing(Transaction::timestamp).reversed())
                .forEach(transaction -> {
            stringBuilder.append("\n");
            stringBuilder.append(formatter.format(transaction));
        });

        output.print(stringBuilder.toString());
    }
}
