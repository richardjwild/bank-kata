package com.codurance.bank;

import java.util.List;

public class Statement {

    private Output output;

    public Statement(Output output) {
        this.output = output;
    }

    public void print(List<Transaction> listOfTransactions) {
        output.print("date || credit || debit || balance");
    }
}
