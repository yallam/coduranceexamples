package accounts;

import accounts.model.Transaction;
import accounts.repository.TransactionRepository;
import accounts.service.AccountServiceImpl;
import accounts.service.Console;
import accounts.service.StatementPrinter;
import accounts.service.TransactionDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountServiceIntegrationTest {
    private AccountServiceImpl subject;
    private final String CONSOLE_HEADER = "DATE || AMOUNT || BALANCE";
    @Mock
    private Console mockConsole;
    @Mock
    private TransactionDate mockTransactionDate;

    @BeforeEach
    public void initialise() {
        TransactionRepository transactionRepository = new TransactionRepository();
        StatementPrinter statementPrinter = new StatementPrinter(mockConsole);
        subject = new AccountServiceImpl(transactionRepository, statementPrinter,mockTransactionDate);
    }

    @Test
    public void StatementShouldContainAllTransactionsWithHeader() {
       given(mockTransactionDate.getDate()).willReturn(LocalDate.of(2020,02,01),LocalDate.of(2020,01,02));
        subject.deposit(new BigDecimal(100));
        subject.withdraw(new BigDecimal(-50));


        ArgumentCaptor<String> transactionTextCaptor = ArgumentCaptor.forClass(String.class);
        subject.printStatement();
        verify(mockConsole, times(3)).print(transactionTextCaptor.capture());
        List<String> actualTransactionTexts = transactionTextCaptor.getAllValues();

        assertEquals(CONSOLE_HEADER, actualTransactionTexts.get(0));
        assertEquals("2020-01-02 || -50.00 || 50.00", actualTransactionTexts.get(1));
        assertEquals("2020-02-01 || 100.00 || 100.00", actualTransactionTexts.get(2));
    }

}
