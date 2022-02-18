Feature: Bank account

  Scenario: Acceptance test
    Given a newly created bank account
    And a deposit of 1000 pounds on "2012-01-10"
    And a deposit of 2000 pounds on "2012-01-13"
    And a withdrawal of 500 pounds on "2012-01-14"
    When a statement is printed
    Then the printed statement shows:
        """
date || credit || debit || balance
14/01/2012 || || 500.00 || 2500.00
13/01/2012 || 2000.00 || || 3000.00
10/01/2012 || 1000.00 || || 1000.00
        """