package bank;

import java.time.LocalDate;

public record StatementLine(
        LocalDate transactionDate,
        Integer depositAmount,
        Integer withdrawalAmount,
        int runningBalance) {
}
