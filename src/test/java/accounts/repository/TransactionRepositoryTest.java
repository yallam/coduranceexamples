package accounts.repository;
import accounts.model.Direction;
import accounts.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TransactionRepositoryTest {
    @Test
    public void shouldBeAbleToReturnTransactionWhenAmountIsDeposited(){

        TransactionRepository transactionRepository = new TransactionRepository();
        Transaction transaction=new Transaction(LocalDate.now(),new BigDecimal(200), Direction.IN);

        transactionRepository.addTransaction(transaction);

       assertEquals(transaction,transactionRepository.getTransactions().get(0));

    }

}
