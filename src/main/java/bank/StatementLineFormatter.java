package bank;

import java.time.format.DateTimeFormatter;

import static java.util.Objects.nonNull;

public class StatementLineFormatter {

    private final DateTimeFormatter timestampFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String format(StatementLine statementLine) {
        if (nonNull(statementLine.depositAmount()))
            return String.format("%s || %d.00 || || %d.00",
                    statementLine.transactionDate().format(timestampFormatter),
                    statementLine.depositAmount(),
                    statementLine.runningBalance());
        else
            return String.format("%s || || %d.00 || %d.00",
                    statementLine.transactionDate().format(timestampFormatter),
                    statementLine.withdrawalAmount(),
                    statementLine.runningBalance());
    }
}
