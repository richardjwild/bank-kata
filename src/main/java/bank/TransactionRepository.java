package bank;

import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {

    private List<BankAccountTransaction> transactions = new ArrayList<>();

    public void store(BankAccountTransaction transaction) {
        transactions.add(transaction);
    }

    public List<BankAccountTransaction> getAllTransactions() {
        return transactions;
    }
}
