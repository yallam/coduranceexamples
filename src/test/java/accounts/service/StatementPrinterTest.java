package accounts.service;

import accounts.model.Direction;
import accounts.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StatementPrinterTest {

    private StatementPrinter subject;
    @Mock
    private Console mockConsole;
    private final String CONSOLE_HEADER = "DATE || AMOUNT || BALANCE";

    @BeforeEach
    public void init() {
        subject = new StatementPrinter(mockConsole);
    }

    @Test
    public void shouldBeAbleToPrintHeaderWithNoTransactionsGiven() {
        List<Transaction> transactions = Collections.EMPTY_LIST;
        subject.printStatement(transactions);

        verify(mockConsole).print(CONSOLE_HEADER);
    }


    @Test
    public void shouldBeAbleToPrintTransactionsInReverseOrder() {

        Transaction transaction1 = new Transaction(LocalDate.of(2020, 1, 8), new BigDecimal(200), Direction.IN);
        Transaction transaction2 = new Transaction(LocalDate.of(2020, 2, 8), new BigDecimal(400), Direction.IN);
        Transaction transaction3 = new Transaction(LocalDate.of(2020, 3, 8), new BigDecimal(-400), Direction.OUT);
        List<Transaction> transactions = List.of(transaction1, transaction2, transaction3);
        ArgumentCaptor<String> transactionTextCaptor = ArgumentCaptor.forClass(String.class);

        subject.printStatement(transactions);
        verify(mockConsole, times(4)).print(transactionTextCaptor.capture());

        List<String> actualTransactionTexts = transactionTextCaptor.getAllValues();

        assertEquals(CONSOLE_HEADER, actualTransactionTexts.get(0));
        assertEquals("2020-03-08 || -400.00 || 200.00", actualTransactionTexts.get(1));
        assertEquals("2020-02-08 || 400.00 || 600.00", actualTransactionTexts.get(2));
        assertEquals("2020-01-08 || 200.00 || 200.00", actualTransactionTexts.get(3));




    }

}
