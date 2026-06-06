package smartATMsystem.project.console.smartATM;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private static final List<Account> accounts = new ArrayList<>();

    static {

        accounts.add(new Account("Nicholas", 3000));
        accounts.add(new Account("Patrick", 5000));
        accounts.add(new Account("Grace", 7000));
        accounts.add(new Account("Claris", 10000));
    }

    public static List<Account> getAccounts() {
        return accounts;
    }

    public static Account findByAccountNumber(String accountNumber) {

        for (Account acc : accounts) {

            if (String.valueOf(acc.getAccountNumber())
                    .equals(accountNumber)) {

                return acc;
            }
        }

        return null;
    }
}