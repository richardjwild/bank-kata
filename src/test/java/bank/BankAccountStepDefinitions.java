package bank;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.when;

public class BankAccountStepDefinitions {

    private BankAccount bankAccount;
    private Timepiece clock = Mockito.mock(Timepiece.class);
    private PrintOutput printOutput = Mockito.mock(PrintOutput.class);
    private StatementLineFormatter transactionFormatter = new StatementLineFormatter();
    private StatementPrinter statementPrinter = new StatementPrinter(printOutput, transactionFormatter);
    private TransactionRepository transactionRepository = new TransactionRepository();

    @Given("a new bank account")
    public void a_new_bank_account() {
        this.bankAccount = new BankAccount(clock, transactionRepository, statementPrinter);
    }

    @Given("a deposit of {int} GBP is made on {localDate}")
    public void a_deposit_of_gbp_is_made_on(Integer amount, LocalDate transactionDate) {
        when(clock.getTimestamp()).thenReturn(transactionDate);
        bankAccount.deposit(amount);
    }

    @Given("a withdrawal of {int} GBP is made on {localDate}")
    public void a_withdrawal_of_gbp_is_made_on(Integer amount, LocalDate transactionDate) {
        when(clock.getTimestamp()).thenReturn(transactionDate);
        bankAccount.withdraw(amount);
    }

    @When("the statement is requested")
    public void the_statement_is_requested() {
        bankAccount.printStatement();
    }

    @Then("the statement is")
    public void the_statement_is(String statement) {
        Mockito.verify(printOutput).print(statement);
    }

    @ParameterType("\\d{2}\\-\\d{2}\\-\\d{4}")
    public LocalDate localDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
