package smartATMsystem.project.console.smartATM;

import java.util.Arrays;
import java.util.List;

public class AccountsData {
	
	public static final List<Account> accountList = Arrays.asList(
			new Account("Nicholas", 3000),
			new Account("Nicodemus", 4000),
			new Account("Patrick", 5000),
			new Account("Grace", 6000),
			new Account("Claris", 7000)
			);

	public static List<Account> getAccountlist() {
		return accountList;
	}
	
	

}
