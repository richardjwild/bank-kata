package bank;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

public class StatementPrinter {

    private final PrintOutput printOutput;
    private final StatementLineFormatter statementLineFormatter;

    public StatementPrinter(PrintOutput printOutput, StatementLineFormatter statementLineFormatter) {
        this.printOutput = printOutput;
        this.statementLineFormatter = statementLineFormatter;
    }

    public void printStatement(int balanceCarriedForward, List<BankAccountTransaction> transactions) {
        var runningBalance = new AtomicInteger(balanceCarriedForward);
        Stream<String> statementLines = transactions.stream()
                .sorted(comparing(BankAccountTransaction::timestamp))
                .map(transaction -> toStatementLine(transaction, runningBalance.addAndGet(transaction.amount())))
                .sorted(comparing(StatementLine::transactionDate).reversed())
                .map(statementLineFormatter::format);
        var statement = new StringBuilder("date || credit || debit || balance\n");
        statementLines.forEach(statementLine -> {
            statement.append(statementLine);
            statement.append("\n");
        });
        printOutput.print(statement.toString());
    }

    private StatementLine toStatementLine(BankAccountTransaction transaction, int runningBalance) {
        if (transaction.amount() >= 0)
            return new StatementLine(transaction.timestamp(), transaction.amount(), null, runningBalance);
        else
            return new StatementLine(transaction.timestamp(), null, Math.abs(transaction.amount()), runningBalance);
    }
}
