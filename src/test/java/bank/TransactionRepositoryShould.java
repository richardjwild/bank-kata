package bank;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.fest.assertions.Assertions.assertThat;

class TransactionRepositoryShould {

    private TransactionRepository transactionRepository = new InMemoryTransactionRepository();

    @Test
    void be_initially_empty() {
        assertThat(transactionRepository.findAllTransactions()).isEmpty();
    }

    @Test
    void return_all_posted_transactions() {
        var t1 = new Transaction(1, LocalDateTime.now());
        var t2 = new Transaction(2, LocalDateTime.now().plusSeconds(1));

        transactionRepository.add(t1);
        transactionRepository.add(t2);

        assertThat(transactionRepository.findAllTransactions()).containsExactly(t1, t2);
    }

}