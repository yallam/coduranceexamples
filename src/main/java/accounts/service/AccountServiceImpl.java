package accounts.service;

import accounts.model.Direction;
import accounts.model.Transaction;
import accounts.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountServiceImpl implements AccountService {
    private final TransactionRepository transactionRepository;
    private final StatementPrinter statementPrinter;
    private TransactionDate transactionDate;

    public AccountServiceImpl(TransactionRepository transactionRepository, StatementPrinter statementPrinter, TransactionDate transactionDate) {
        this.transactionRepository = transactionRepository;
        this.statementPrinter = statementPrinter;
        this.transactionDate = transactionDate;
    }

    @Override
    public void deposit(BigDecimal amount) {
        Transaction depositTransaction = getTransaction(amount);
        transactionRepository.addTransaction(depositTransaction);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        Transaction withDrawTransaction = getTransaction(amount);
        transactionRepository.addTransaction(withDrawTransaction);
    }

    @Override
    public void printStatement() {
        statementPrinter.printStatement(transactionRepository.getTransactions());
    }

    private Transaction getTransaction(BigDecimal amount) {
        return new Transaction(transactionDate.getDate(), amount, amount.intValue() >= 0 ? Direction.IN : Direction.OUT);
    }

}
