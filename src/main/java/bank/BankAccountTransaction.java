package bank;

import java.time.LocalDate;

public record BankAccountTransaction(int amount, LocalDate timestamp) {

}
