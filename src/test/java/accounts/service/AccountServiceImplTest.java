package accounts.service;

import accounts.model.Direction;
import accounts.model.Transaction;
import accounts.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    private AccountServiceImpl subject;

    @Mock
    private TransactionRepository mockTransactionRepository;

    @BeforeEach
    public void init() {
        subject = new AccountServiceImpl(mockTransactionRepository);
    }


    @Test
    public void shouldAddDepositTransactionWhileDepositingAnAmount() {
        subject.deposit(new BigDecimal(200));
        Transaction expectedTransaction = new Transaction(
                LocalDate.now(), new BigDecimal(200), Direction.IN);
        verify(mockTransactionRepository).addTransaction(expectedTransaction);
    }
    @Test
    public void shouldBeAbleToAddTransactionWhenMoneyWithdrawn(){
        subject.withdraw(new BigDecimal(-100));
        Transaction expectedTransaction = new Transaction(
                LocalDate.now(), new BigDecimal(-100), Direction.OUT);
        verify(mockTransactionRepository).addTransaction(expectedTransaction);

    }


//    @Test
//    public void shouldBeAbleToPrintTransactions() {
//        subject.deposit(new BigDecimal(200));
//        subject.withdraw(new BigDecimal(-100));
//        subject.withdraw(new BigDecimal(-10));
//        subject.deposit(new BigDecimal(200));
//        subject.printStatement();
//    }
}
