package bank;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryShall {

    private TransactionRepository transactionRepository = new TransactionRepository();

    @Test
    void store_transactions() {
        var transactions = List.of(
                new BankAccountTransaction(1, LocalDate.of(2022, 12, 20)),
                new BankAccountTransaction(2, LocalDate.of(2022, 12, 21)));
        transactionRepository.store(transactions.get(0));
        transactionRepository.store(transactions.get(1));
        assertEquals(transactions, transactionRepository.getAllTransactions());
    }
}