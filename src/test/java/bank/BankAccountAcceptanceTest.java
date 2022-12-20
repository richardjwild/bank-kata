package bank;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.time.LocalDateTime.of;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BankAccountAcceptanceTest {

//    @Mock
//    PrintOutput output;
//
//    @Mock
//    private Clock clock;
//
//    @Test
//    void print_a_statement_showing_posted_transactions() {
//        var transactionRepository = new InMemoryTransactionRepository();
//        var statement = new StatementPrinter(new StatementFormatter(), output);
//        var bankAccount = new BankAccount(transactionRepository, clock, statement);
//
//        when(clock.currentTime())
//                .thenReturn(of(2012, 1, 10, 0, 0))
//                .thenReturn(of(2012, 1, 13, 0, 0))
//                .thenReturn(of(2012, 1, 14, 0, 0));
//
//        bankAccount.deposit(1000);
//        bankAccount.deposit(2000);
//        bankAccount.withdraw(500);
//
//        bankAccount.statement();
//        verify(output).print("date || credit || debit || balance\n" +
//                "14/01/2012 || || 500.00 || 2500.00\n" +
//                "13/01/2012 || 2000.00 || || 3000.00\n" +
//                "10/01/2012 || 1000.00 || || 1000.00");
//    }

}
