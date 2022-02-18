package bank.features;

import bank.*;
import bank.helpers.InMemoryTransactionRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BankAccountStepDefs {

    private BankAccount bankAccount;
    private Clock clock;
    private PrintOutput printOutput;

    @Given("a newly created bank account")
    public void a_newly_created_bank_account() {
        var transactionRepository = new InMemoryTransactionRepository();
        var statementFormatter = new StatementFormatter();
        printOutput = mock(PrintOutput.class);
        var statementPrinter = new StatementPrinter(statementFormatter, printOutput);
        clock = mock(Clock.class);
        bankAccount = new BankAccount(transactionRepository, clock, statementPrinter);
    }

    @Given("a deposit of {int} pounds on {string}")
    public void a_deposit_of_pounds_on(Integer amount, String date) {
        given(clock.currentTime()).willReturn(LocalDate.parse(date, ISO_LOCAL_DATE).atStartOfDay());
        bankAccount.deposit(amount);
    }

    @Given("a withdrawal of {int} pounds on {string}")
    public void a_withdrawal_of_pounds_on(Integer amount, String date) {
        given(clock.currentTime()).willReturn(LocalDate.parse(date, ISO_LOCAL_DATE).atStartOfDay());
        bankAccount.withdraw(amount);
    }

    @When("a statement is printed")
    public void a_statement_is_printed() {
        bankAccount.statement();
    }

    @Then("the printed statement shows:")
    public void the_printed_statement_shows(String content) {
        verify(printOutput).print(content);
    }
}
