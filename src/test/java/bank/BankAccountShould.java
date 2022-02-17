package bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountShould {

    @Mock
    TransactionRepository transactionRepository;

    @Mock
    Clock clock;

    @Mock
    StatementPrinter statement;

    @InjectMocks
    private BankAccount account;

    @Test
    void post_a_deposit() {
        var amount = 10;
        var timestamp = LocalDateTime.now();
        when(clock.currentTime()).thenReturn(timestamp);

        account.deposit(amount);

        var depositTransaction = new Transaction(amount, timestamp);
        verify(transactionRepository).add(depositTransaction);
    }

    @Test
    void post_a_withdrawal() {
        var timestamp = LocalDateTime.now();
        var amount = 10;
        when(clock.currentTime()).thenReturn(timestamp);

        account.withdraw(amount);

        var withdrawalTransaction = new Transaction(-amount, timestamp);
        verify(transactionRepository).add(withdrawalTransaction);
    }

    @Test
    void print_a_statement() {
        var listOfTransactions = asList(aTransaction(), aTransaction());
        when(transactionRepository.findAllTransactions()).thenReturn(listOfTransactions);

        account.statement();

        verify(statement).print(listOfTransactions);
    }

    private Transaction aTransaction() {
        return new Transaction(1, LocalDateTime.now());
    }

}