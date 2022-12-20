package bank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankAccountShall {

    private Timepiece timepiece;
    private BankAccount bankAccount;
    private TransactionRepository transactionRepository;
    private StatementPrinter statementPrinter;

    @BeforeEach
    void setup() {
        timepiece = mock(Timepiece.class);
        transactionRepository = mock(TransactionRepository.class);
        statementPrinter = mock(StatementPrinter.class);
        bankAccount = new BankAccount(timepiece, transactionRepository, statementPrinter);
    }

    @Test
    void store_transactions() {
        var amounts = new int[]{2, 1};
        var timestamps = new LocalDate[]{
                LocalDate.of(2022, 12, 20),
                LocalDate.of(2022, 12, 21)};
        when(timepiece.getTimestamp()).thenReturn(timestamps[0], timestamps[1]);
        bankAccount.deposit(amounts[0]);
        bankAccount.withdraw(amounts[1]);
        var inOrder = inOrder(transactionRepository);
        inOrder.verify(transactionRepository).store(new BankAccountTransaction(amounts[0], timestamps[0]));
        inOrder.verify(transactionRepository).store(new BankAccountTransaction(amounts[1] * -1, timestamps[1]));
    }

    @Test
    void print_a_statement() {
        var transactions = List.of(
                new BankAccountTransaction(2, LocalDate.of(2022, 12, 20)),
                new BankAccountTransaction(1, LocalDate.of(2022, 12, 21)));
        when(transactionRepository.getAllTransactions()).thenReturn(transactions);
        bankAccount.printStatement();
        verify(statementPrinter).printStatement(0, transactions);
    }

}