package com.codurance.bank;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class StatementPrinter {

    private final PrintOutput output;
    private final StatementFormatter statementFormatter;

    public StatementPrinter(StatementFormatter statementFormatter, PrintOutput output) {
        this.output = output;
        this.statementFormatter = statementFormatter;
    }

    public void print(List<Transaction> transactions) {
        var stringBuilder = new StringBuilder();
        stringBuilder.append("date || credit || debit || balance");
        statementLines(transactions)
                .sorted(Comparator.comparing(StatementLine::timestamp).reversed())
                .forEach(statementLine -> {
            stringBuilder.append("\n");
            stringBuilder.append(statementFormatter.format(statementLine));
        });
        output.print(stringBuilder.toString());
    }

    private Stream<StatementLine> statementLines(List<Transaction> listOfTransactions) {
        var newBalance = new AtomicInteger(0);
        return listOfTransactions.stream()
                .map(transaction -> new StatementLine(transaction, newBalance.addAndGet(transaction.amount())));
    }

}
