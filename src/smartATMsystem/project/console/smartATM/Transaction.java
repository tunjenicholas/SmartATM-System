package smartATMsystem.project.console.smartATM;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private TransactionType type;
    private double amount;
    private LocalDateTime dateTime;
    private double balanceAfter;

    public Transaction(TransactionType type, double amount, double balanceAfter) {
        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = LocalDateTime.now();
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    @Override
    public String toString() {

        DateTimeFormatter format =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        return String.format(
                "%-20s KES %-10.2f %s Balance: %.2f",
                type,
                amount,
                dateTime.format(format),
                balanceAfter
        );
    }
}