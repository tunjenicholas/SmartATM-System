package smartATMsystem.project.console.smartATM;

public class ATMService {

    private final InputUtil input = new InputUtil();

    private Account loggedInAccount;

    public void login() {

        System.out.println();
        System.out.println("==========================");
        System.out.println("        SMART ATM");
        System.out.println("==========================");

        String accountNumber =
                input.readString("Enter Account Number: ");

        Account found =
                AccountRepository.findByAccountNumber(accountNumber);

        if (found == null) {

            System.err.println("Account not found.");
            return;
        }

        int attempts = 3;

        while (attempts > 0) {

            int enteredPin =
                    input.readInt("Enter PIN: ");

            if (enteredPin == found.getPin()) {

                loggedInAccount = found;

                System.out.println(
                        "\nLogin Successful. Welcome "
                                + found.getOwnerName()
                );

                menu();
                return;
            }

            attempts--;

            System.err.println(
                    "Invalid PIN. Attempts remaining: "
                            + attempts
            );
        }

        System.err.println("Account blocked.");
    }

    private void menu() {

        while (true) {

            System.out.println();
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Mini Statement");
            System.out.println("6. Change PIN");
            System.out.println("7. Exit");
            System.out.println();

            int option =
                    input.readInt("Choose option: ");

            switch (option) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    deposit();
                    break;

                case 3:
                    withdraw();
                    break;

                case 4:
                    transfer();
                    break;

                case 5:
                    miniStatement();
                    break;

                case 6:
                    changePin();
                    break;

                case 7:
                    System.out.println(
                            "Thank you for using Smart ATM."
                    );
                    return;

                default:
                    System.err.println("Invalid option.");
            }
        }
    }

    private void checkBalance() {

        System.out.printf(
                "Current Balance: %.2f%n",
                loggedInAccount.getBalance()
        );
    }

    private void deposit() {

        double amount =
                input.readDouble("Enter amount: ");

        int pin =
                input.readInt("Enter PIN: ");

        if (pin != loggedInAccount.getPin()) {

            System.err.println("Invalid PIN.");
            return;
        }

        try {

            loggedInAccount.deposit(amount);

            System.out.printf(
                    "Deposit successful. Balance: %.2f%n",
                    loggedInAccount.getBalance()
            );

        } catch (IllegalArgumentException e) {

            System.err.println(e.getMessage());
        }
    }

    private void withdraw() {

        double amount =
                input.readDouble("Enter amount: ");

        int pin =
                input.readInt("Enter PIN: ");

        if (pin != loggedInAccount.getPin()) {

            System.err.println("Invalid PIN.");
            return;
        }

        boolean success =
                loggedInAccount.withdraw(amount);

        if (success) {

            System.out.printf(
                    "Withdrawal successful. Balance: %.2f%n",
                    loggedInAccount.getBalance()
            );

        } else {

            System.err.println(
                    "Withdrawal failed."
            );
        }
    }

    private void transfer() {

        String receiverAcc =
                input.readString(
                        "Enter receiver account: "
                );

        Account receiver =
                AccountRepository.findByAccountNumber(
                        receiverAcc
                );

        if (receiver == null) {

            System.err.println("Receiver not found.");
            return;
        }

        double amount =
                input.readDouble("Enter amount: ");

        int pin =
                input.readInt("Enter PIN: ");

        if (pin != loggedInAccount.getPin()) {

            System.err.println("Invalid PIN.");
            return;
        }

        boolean success =
                loggedInAccount.transfer(
                        receiver,
                        amount
                );

        if (success) {

            System.out.printf(
                    "Transfer successful to %s%n",
                    receiver.getOwnerName()
            );

        } else {

            System.err.println(
                    "Transfer failed."
            );
        }
    }

    private void miniStatement() {

        System.out.println();
        System.out.println("========== MINI STATEMENT ==========");

        if (loggedInAccount.getTransactions().isEmpty()) {

            System.out.println("No transactions found.");
            return;
        }

        for (Transaction transaction :
                loggedInAccount.getTransactions()) {

            System.out.println(transaction);
        }
    }

    private void changePin() {

        int oldPin =
                input.readInt("Enter old PIN: ");

        int newPin =
                input.readInt("Enter new PIN: ");

        boolean changed =
                loggedInAccount.changePin(
                        oldPin,
                        newPin
                );

        if (changed) {

            System.out.println(
                    "PIN changed successfully."
            );

        } else {

            System.err.println(
                    "PIN change failed."
            );
        }
    }
}
