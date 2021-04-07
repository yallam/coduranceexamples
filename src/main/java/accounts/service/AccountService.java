package accounts.service;

import accounts.model.Transaction;

import java.math.BigDecimal;

public interface AccountService {
    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    void printStatement();
}
