package accounts.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Transaction {
    private LocalDate date;
    private BigDecimal amount;
    private Direction direction;

    public Transaction(LocalDate date, BigDecimal amount, Direction direction) {
        this.date = date;
        this.amount = amount;
        this.direction = direction;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return date.equals(that.date) && amount.equals(that.amount) && direction.equals(that.direction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, amount, direction);
    }
}
