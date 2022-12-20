package bank;

public class BankAccount {

    private final Timepiece clock;
    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;

    public BankAccount(Timepiece clock, TransactionRepository transactionRepository, StatementPrinter statementPrinter) {
        this.clock = clock;
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
    }

    public void deposit(Integer amount) {
        var transaction = new BankAccountTransaction(amount, clock.getTimestamp());
        transactionRepository.store(transaction);
    }

    public void withdraw(Integer amount) {
        var transaction = new BankAccountTransaction(amount * -1, clock.getTimestamp());
        transactionRepository.store(transaction);
    }

    public void printStatement() {
        statementPrinter.printStatement(0, transactionRepository.getAllTransactions());
    }
}
