package bank;

import java.time.format.DateTimeFormatter;

public class StatementFormatter {

    public String format(StatementLine statementLine) {
        if (statementLine.amount() > 0) {
            return String.format("%s || %.2f || || %.2f",
                    statementLine.timestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    (float) statementLine.amount(),
                    (float) statementLine.balance());
        } else {
            return String.format("%s || || %.2f || %.2f",
                    statementLine.timestamp().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    (float) statementLine.amount() * -1,
                    (float) statementLine.balance());
        }
    }
}
