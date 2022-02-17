package bank;

public class BankAccount {

    private final TransactionRepository repository;
    private final Clock clock;
    private final StatementPrinter statementPrinter;

    public BankAccount(TransactionRepository repository, Clock clock, StatementPrinter statementPrinter) {
        this.repository = repository;
        this.clock = clock;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(int amount) {
        createTransaction(amount);
    }

    public void withdraw(int amount) {
        createTransaction(amount * -1);
    }

    private void createTransaction(int amount) {
        var transaction = new Transaction(amount, clock.currentTime());
        repository.add(transaction);
    }

    public void statement() {
        statementPrinter.print(repository.findAllTransactions());
    }
}
