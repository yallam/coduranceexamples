package accounts;

public class Account implements AccountService {
    int balance = 0;

    @Override
    public void deposit(int amount) {
        balance += amount;

    }

    @Override
    public void withdraw(int amount) {
        balance = balance - amount;
    }

    @Override
    public void printStatement() {

    }
}
