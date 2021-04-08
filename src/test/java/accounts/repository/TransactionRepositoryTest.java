package accounts.repository;
import accounts.model.Direction;
import accounts.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TransactionRepositoryTest {
    @Test
    public void shouldBeAbleToReturnTransactionWhenAmountIsDeposited(){

        TransactionRepository transactionRepository = new TransactionRepository();
        Transaction transaction1=new Transaction(LocalDate.of(2020,2,2),new BigDecimal(200), Direction.IN);
        Transaction transaction2=new Transaction(LocalDate.now(),new BigDecimal(400), Direction.IN);
        Transaction transaction3=new Transaction(LocalDate.now(),new BigDecimal(-100), Direction.OUT);
        List<Transaction> transactions = List.of(transaction1,transaction2,transaction3);


        transactionRepository.addTransaction(transaction1);
        transactionRepository.addTransaction(transaction2);
        transactionRepository.addTransaction(transaction3);

       assertEquals(transactions,transactionRepository.getTransactions());

    }

}
