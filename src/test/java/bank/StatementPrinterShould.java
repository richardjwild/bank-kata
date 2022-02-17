package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementPrinterShould {

    private static final List<Transaction> NO_TRANSACTIONS = emptyList();

    @Mock
    private PrintOutput output;

    @Mock
    private Transaction transaction, earlierTransaction;

    private StatementPrinter statementPrinter;

    @BeforeEach
    public void setup() {
        statementPrinter = new StatementPrinter(new StatementFormatter(), output);
    }

    @Test
    void print_an_empty_statement() {
        statementPrinter.print(NO_TRANSACTIONS);

        verify(output).print("date || credit || debit || balance");
    }

    @Test
    void print_a_formatted_credit_transaction() {
        when(transaction.timestamp()).thenReturn(LocalDateTime.of(2022, 02, 16, 0, 0));
        when(transaction.amount()).thenReturn(100);

        statementPrinter.print(asList(transaction));

        verify(output).print(
                "date || credit || debit || balance\n" +
                "16/02/2022 || 100.00 || || 100.00");
    }

    @Test
    void print_a_formatted_debit_transaction() {
        when(transaction.timestamp()).thenReturn(LocalDateTime.of(2022, 02, 16, 0, 0));
        when(transaction.amount()).thenReturn(-100);

        statementPrinter.print(asList(transaction));

        verify(output).print(
                "date || credit || debit || balance\n" +
                "16/02/2022 || || 100.00 || -100.00");
    }

    @Test
    void print_transactions_in_reverse_date_order() {
        var timestamp = LocalDateTime.of(2022, 02, 16, 0, 0);
        when(transaction.timestamp()).thenReturn(timestamp);
        when(transaction.amount()).thenReturn(-100);
        when(earlierTransaction.timestamp()).thenReturn(timestamp.minusDays(1));
        when(earlierTransaction.amount()).thenReturn(100);

        statementPrinter.print(asList(earlierTransaction, transaction));

        verify(output).print(
                "date || credit || debit || balance\n" +
                "16/02/2022 || || 100.00 || 0.00\n" +
                "15/02/2022 || 100.00 || || 100.00");
    }
}