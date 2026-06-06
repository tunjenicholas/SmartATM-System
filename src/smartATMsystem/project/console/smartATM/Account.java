package smartATMsystem.project.console.smartATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;

public class Account {

    private static final Set<Long> USED_ACCOUNT_NUMBERS = new HashSet<>();

    private long accountNumber;
    private int pin;
    private String ownerName;
    private double balance;

    private List<Transaction> transactions = new ArrayList<>();

    public Account(String ownerName, double balance) {

        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException(
                    "Account holder name required"
            );
        }

        if (!ownerName.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException(
                    "Name should contain letters only"
            );
        }

        if (balance < 1000) {
            throw new IllegalArgumentException(
                    "Minimum balance is 1000"
            );
        }

        this.ownerName = ownerName;
        this.balance = balance;

        this.accountNumber = generateAccountNumber();
        this.pin = generatePin();
    }

    private long generateAccountNumber() {

        Random rand = new Random();

        long min = 10000000000L;
        long max = 99999999999L;

        long generated;

        do {

            generated =
                    min + (long) (rand.nextDouble() * (max - min));

        } while (USED_ACCOUNT_NUMBERS.contains(generated));

        USED_ACCOUNT_NUMBERS.add(generated);

        return generated;
    }

    private int generatePin() {

        return 1000 + new Random().nextInt(9000);
    }

    // ===============================
    // BUSINESS METHODS
    // ===============================

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be greater than zero"
            );
        }

        balance += amount;

        transactions.add(
                new Transaction(
                        TransactionType.DEPOSIT,
                        amount,
                        balance
                )
        );
    }

    public boolean withdraw(double amount) {

        if (amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(
                new Transaction(
                        TransactionType.WITHDRAW,
                        amount,
                        balance
                )
        );

        return true;
    }

    public boolean transfer(Account receiver, double amount) {

        if (receiver == null) {
            return false;
        }

        if (receiver == this) {
            return false;
        }

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        receiver.balance += amount;

        transactions.add(
                new Transaction(
                        TransactionType.TRANSFER_SENT,
                        amount,
                        balance
                )
        );

        receiver.transactions.add(
                new Transaction(
                        TransactionType.TRANSFER_RECEIVED,
                        amount,
                        receiver.balance
                )
        );

        return true;
    }

    public boolean changePin(int oldPin, int newPin) {

        if (this.pin != oldPin) {
            return false;
        }

        String pinStr = String.valueOf(newPin);

        if (!pinStr.matches("\\d{4}")) {
            return false;
        }

        this.pin = newPin;

        transactions.add(
                new Transaction(
                        TransactionType.PIN_CHANGE,
                        0,
                        balance
                )
        );

        return true;
    }

    // ===============================
    // GETTERS
    // ===============================

    public long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {

        return "\n==================================" +
                "\nAccount Created Successfully" +
                "\n==================================" +
                "\nName          : " + ownerName +
                "\nAccount No    : " + accountNumber +
                "\nPIN           : " + pin +
                "\nBalance       : " + balance;
    }
}