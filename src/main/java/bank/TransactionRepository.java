package bank;

import java.util.List;

public interface TransactionRepository {

    void add(Transaction transaction);

    List<Transaction> findAllTransactions();
}
