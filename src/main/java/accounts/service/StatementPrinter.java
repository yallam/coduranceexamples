package accounts.service;

import accounts.model.Transaction;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {
    private static final String CONSOLE_HEADER = "DATE || AMOUNT || BALANCE";
    private Console console;
    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public StatementPrinter(Console console) {
        this.console = console;
    }

    public void printStatement(List<Transaction> transactions) {
        console.print(CONSOLE_HEADER);
        printStatementLines(transactions);
    }

    private void printStatementLines(List<Transaction> transactions) {
        AtomicInteger balance = new AtomicInteger(0);
        transactions.stream()
                .map(transaction -> getTransactionText(transaction, balance))
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator()
                .forEachRemaining(console::print);
    }

    private String getTransactionText(Transaction transaction, AtomicInteger balance) {
        return transaction.getDate().toString() + " || "
                + decimalFormat.format(transaction.getAmount()) + " || "
                + decimalFormat.format(balance.addAndGet(transaction.getAmount().intValue()));
    }
}
