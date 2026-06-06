package smartATMsystem.project.console.smartATM;

public class ATMApp {

    public static void main(String[] args) {

        System.out.println("AVAILABLE ACCOUNTS");

        for (Account acc :
                AccountRepository.getAccounts()) {

            System.out.println(acc);
            System.out.println();
        }

        ATMService atm = new ATMService();
        atm.login();
    }
}