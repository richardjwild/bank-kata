package bank;

import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BankAccountStepDefinitions {

    @Given("a new bank account")
    public void a_new_bank_account() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("a deposit of {int} GBP is made on {localDate}")
    public void a_deposit_of_gbp_is_made_on(Integer amount, LocalDate transactionDate) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("a withdrawal of {int} GBP is made on {localDate}")
    public void a_withdrawal_of_gbp_is_made_on(Integer amount, LocalDate transactionDate) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the statement is requested")
    public void the_statement_is_requested() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the statement is")
    public void the_statement_is(String statement) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @ParameterType("\\d{2}\\-\\d{2}\\-\\d{4}")
    public LocalDate localDate(String dateString) {
        return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
