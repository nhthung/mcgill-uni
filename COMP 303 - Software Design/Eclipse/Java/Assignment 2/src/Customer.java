/* ============================================================================
 * File Name: Customer.java
 * ============================================================================
 * Developer: Le Nhat Hung
 * Purpose: Define Customer class/object, its properties, methods.
 * Special notes:
			Each customer has an unique int 'customerNumber'
			and ArrayList<Account> 'accounts'.

			1st customer has number 0, 2nd is 1, etc..
			This is achieved through a 'customerCount' static int, and used as
			indexing for the 'customers' array list in Account.java.

			User-inputed account numbers in Account.java's UI will also be used
			indexing for this class' 'accounts' array list.

 * ============================================================================
 */

import java.util.ArrayList;

public class Customer {
	private static int customerCount = 0;

	private 			 String 						name;
	private 			 int 								customerNumber;
	private 			 double 						discountPercentage;
	private				 ArrayList<Account> accounts;



	/* Name: Customer
	 * Purpose: Constructor. Assign customer name, 'accounts' array list, discount %.
	 * Inputs: String 'name', double 'discountPercentage'.
	 * Side effects: Increments 'customerCount'
	 */
	Customer(String name, double discountPercentage) {
		this.name 							= name;
		this.accounts 					= new ArrayList<Account>();
		this.discountPercentage = discountPercentage;

		customerNumber = customerCount++;
	}

	/* Name: addAccount
	 * Purpose: Add checking or savings account object
				with chosen initial deposit into 'accounts' array list.

	 * Inputs: char 'accountSwitch', double 'amount'.
	 * Special notes: In UI, user will input either 'a' for checking or 'b' for savings account.
	 */
	void addAccount(char accountSwitch, double amount) {

		switch (accountSwitch) {
		case 'a': accounts.add(new Checking(amount)); break;
		case 'b': accounts.add(new Savings(amount)); break;
		}
	}

	/* Name: getName
	 * Purpose: Return customer name as String.
	 * Outputs: String 'name'.
	 */
	String getName() {
		return name;
	}

	/* Name: deposit
	 * Purpose: Deposit inputed amount into account at inputed array list index of 'accounts'.
	 * Inputs: int 'account', double 'amount'.
	 * Outputs: Boolean. True if
				- amount non zero
				- Savings.savingsDeposit( amount + discountPercentage/100 ) true
				- or Account.deposit(amount) true

	 * Special notes:
				Method exists to avoid giving user direct access to
				'accounts' array list.

				Method distinguishes savings from checking account
				by checking object's class name ("Savings" or "Checking")

				If dealing with savings accounts,
				calls savingsDeposit( amount + discount %/100).
				If with checking accounts,
				calls Account.deposit(amount)
				(checking account deposits have no conditions).
	 */
	boolean deposit(int account, double amount) {
		if ( amount == 0 )
			return false;

		Account Account = accounts.get(account);

		if ( Account.getClass().getName().equals("Savings") )
			return ((Savings)Account).savingsDeposit( amount + discountPercentage/100 );
		else
			return Account.deposit(amount);
	}

	/* Name: withdraw
	 * Purpose: Withdraw inputed amount from inputed account (like above).
	 * Inputs: int 'account', double 'amount'.
	 * Outputs: Boolean. True if
				- savingsWithdraw(amount) true
				- or checkingWithdraw( amount - discount %/100) true

	 * Special notes:
				Method exists to avoid giving user direct access to
				'accounts' array list.

				Account type distinction like above.
	 */
	boolean withdraw(int account, double amount) {
		Account Account = accounts.get(account);

		if ( Account.getClass().getName().equals("Savings") )
			return ( (Savings)Account ).savingsWithdraw(amount);
		else
			return ( (Checking)Account ).checkingWithdraw( amount - discountPercentage/100 );
	}

	/* Name: transfer
	 * Purpose: Transfer 'amount' using 'accounts' array list indexing.
	 * Inputs: double 'amount', int 'srcAccount', int 'destAccount'.
	 * Outputs: Boolean. True if
				- srcAccount != destAccount
				- and srcAccount.transfer( amount, DestAccount ) true
	 */
	boolean transfer(double amount, int srcAccount, int destAccount) {
		Account SrcAccount;
		Account DestAccount;

		if (srcAccount == destAccount)
			return false;

		SrcAccount 	= accounts.get(srcAccount	);
		DestAccount = accounts.get(destAccount);

		return SrcAccount.transfer( amount, DestAccount );
	}

	/* Name: getAccountList
	 * Purpose: Serve UI. Return customer's account list.
	 * Outputs: String
				"1. Checking - balance [balance]
				 2. Savings - balance [balance]
				 3. Checking - balance [balance]" (etc.)
	 */
	String getAccountList() {
		String accountList = "";

		for( int i = 0; i < accounts.size(); i++ )
			 accountList += (i + 1) + ". " + accounts.get(i) + "\n";

		return accountList;
	}

	/* Name: getAccountCount
	 * Purpose: Return customer's total number of accounts.
	 * Outputs: int accounts.size().
	 * Special notes: Used in Bank.java to verify account input.
	 */
	int getAccountCount() {
		return accounts.size();
	}

	/* Name: getBalance
	 * Purpose: Return an account's balance.
	 * Outputs: double accounts.get(account).getBalance().
	 */
	double getBalance(int account) {
		return accounts.get(account).getBalance();
	}

	/* Name: getCustomerNumber.
	 * Purpose: return customer's number.
	 * Outputs: int customerNumber.
	 */
	int getCustomerNumber() {
		return customerNumber;
	}

	/* Name: toString
	 * Purpose: Override. Serve UI. Print customer info.
	 * Outputs: String "Customer: [name]. Customer number: [number]".
	 */
	public String toString() {
		return "Customer: " + name + ". Customer number: " + customerNumber;
	}
}
