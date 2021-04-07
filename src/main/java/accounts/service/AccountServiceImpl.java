package accounts.service;

import accounts.model.Direction;
import accounts.model.Transaction;
import accounts.repository.TransactionRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountServiceImpl implements AccountService {
    private final TransactionRepository transactionRepository;

    public AccountServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
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
    public void printStatement() {// string buffer-string builder
        System.out.println("DATE       || AMOUNT || BALANCE");
        BigDecimal balance = new BigDecimal(0);
        for (Transaction transaction : transactionRepository.getTransactions()) {
            balance = balance.add(transaction.getAmount());
            System.out.println(transaction.getDate() + " || " + transaction.getAmount() + "    || " + balance);
        }
    }

    private Transaction getTransaction(BigDecimal amount) {
        return new Transaction(LocalDate.now(), amount, amount.intValue() >= 0 ? Direction.IN : Direction.OUT);
    }

}
