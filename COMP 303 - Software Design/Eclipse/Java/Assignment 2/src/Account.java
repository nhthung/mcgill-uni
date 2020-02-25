/* ===============================================================
 * File Name: Account.java
 * ===============================================================
 * Developer: Le Nhat Hung
 * Purpose: Define Account class/object, its properties + methods.
 * ===============================================================
 */

public class Account {
	private double balance;


	/* Name: Account
	 * Purpose: Constructor. Assign value to balance.
	 * Inputs: double 'amount'.
	 */
	Account(double amount) { balance = amount; }

	/* Name: deposit
	 * Purpose: Increase balance.
	 * Inputs: double 'amount'.
	 * Outputs: Boolean. True if 'amount' non negative.
	 */
	boolean deposit(double amount) {
		if (amount <= 0)
			return false;

		balance += amount;
		return true;
	}

	/* Name: withdraw
	 * Purpose: Decrease balance.
	 * Inputs: double 'amount'.
	 * Outputs: Boolean. True if 'amount' non negative nor exceeding balance.
	 */
	boolean withdraw(double amount) {
		if ( amount <= 0 || amount > balance )
			return false;

		balance -= amount;
		return true;
	}

	/* Name: transfer
	 * Purpose: Withdraw from 1 account and deposit in another by same value.
	 * Inputs: double 'amount', Account 'DestAccount'.
	 * Outputs: Boolean. True on success of both withdrawal and deposit.
	 */
	boolean transfer( double amount, Account DestAccount ) {
		if ( withdraw(amount) )
			return DestAccount.deposit(amount);

		return false;
	}

	/* Name: getBalance
	 * Purpose: return balance as double.
	 * Outputs: double 'balance'.
	 */
	double getBalance() {
		return balance;
	}

}
