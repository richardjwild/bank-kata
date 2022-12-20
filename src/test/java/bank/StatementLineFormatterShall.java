package bank;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class StatementLineFormatterShall {

    private StatementLineFormatter statementLineFormatter = new StatementLineFormatter();

    @Test
    void format_a_deposit_line() {
        var depositLine = new StatementLine(LocalDate.of(2022, 12, 20), 1, null, 1);
        var actual = statementLineFormatter.format(depositLine);
        assertEquals("20/12/2022 || 1.00 || || 1.00", actual);
    }

    @Test
    void format_a_withdrawal_line() {
        var withdrawalLine = new StatementLine(LocalDate.of(2022, 12, 20), null, 1, 1);
        var actual = statementLineFormatter.format(withdrawalLine);
        assertEquals("20/12/2022 || || 1.00 || 1.00", actual);
    }

}