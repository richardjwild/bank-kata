package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.*;

class StatementPrinterShall {

    private StatementPrinter statementPrinter;
    private PrintOutput printOutput;
    private StatementLineFormatter statementLineFormatter;

    @BeforeEach
    void setup() {
        printOutput = mock(PrintOutput.class);
        statementLineFormatter = mock(StatementLineFormatter.class);
        statementPrinter = new StatementPrinter(printOutput, statementLineFormatter);
    }

    @Test
    void print_an_empty_statement() {
        statementPrinter.printStatement(0, emptyList());
        verify(printOutput).print("date || credit || debit || balance\n");
    }

    @Test
    void print_a_statement_with_transactions_in_reverse_date_order() {
        var transactions = List.of(
                new BankAccountTransaction(10, LocalDate.of(2022, 12, 20)),
                new BankAccountTransaction(-5, LocalDate.of(2022, 12, 21)));
        when(statementLineFormatter.format(new StatementLine(LocalDate.of(2022, 12, 21), null, 5, 5))).thenReturn("21/12/2022 || || 5.00 || 5.00");
        when(statementLineFormatter.format(new StatementLine(LocalDate.of(2022, 12, 20), 10, null, 10))).thenReturn("20/12/2022 || 10.00 || || 10.00");
        statementPrinter.printStatement(0, transactions);
        verify(printOutput).print("""
                date || credit || debit || balance
                21/12/2022 || || 5.00 || 5.00
                20/12/2022 || 10.00 || || 10.00
                """);
    }

}