Feature: Bank Account

  Scenario: Add transactions and obtain statement
    Given a new bank account
    And a deposit of 1000 GBP is made on 10-01-2022
    And a deposit of 2000 GBP is made on 13-01-2022
    And a withdrawal of 500 GBP is made on 14-01-2022
    When the statement is requested
    Then the statement is
    """
date || credit || debit || balance
14/01/2022 || || 500.00 || 2500.00
13/01/2022 || 2000.00 || || 3000.00
10/01/2022 || 1000.00 || || 1000.00

    """
