/* ==============================================================
 * File Name: Main.java
 * ==============================================================
 * Developer: Le Nhat Hung
 * Purpose: Define Bank class/object, UI, methods to
			- show menu
			- create a customer
			- create an account
			- view balance
			- deposit
			- withdraw
			- choose customer
			- choose account
			- choose amount (to deposit, withdraw, transfer)

 * Inputs: User inputed menu choice, customer, account, amount.
 * Outputs: UI Strings.
 * Special notes:
			In class is defined a 'customers' array list holding
			every customer created.
			The array list's indexes correspond to the
			customer numbers.

 * ==============================================================
 * Modifications
 * ==============================================================
 * February 1 2018 - First half done.
 * February 2 2018 - Last half done.
 * February 3 2018 - Documentation polished.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class Bank {
	static Scanner 						 scan 		 = new Scanner(System.in);
	static ArrayList<Customer> customers = new ArrayList<Customer>();


	/* Name: createCustomer
	 * Purpose: UI to create a customer.
	 * Inputs: User inputed name and discount.
	 * Side effect: UI prints. Add new Customer object into 'customers' array list.
	 * Special notes:
				Method calls helper method isPositiveDouble
				to verify inputed discount %.
	 */
	private static void createCustomer() {
		String name;
		String discountPercentage;


		System.out.print("\nNow creating new customer.\nName: ");
		name = scan.nextLine();

		// Infinite loop until valid discount is inputed.
		while (true) {
			System.out.print("Discount percentage: ");
			discountPercentage = scan.nextLine();

			// Discount invalid if isn't a positive double or > 100.
			if (     !isPositiveDouble(discountPercentage)
			 	   || Double.parseDouble(discountPercentage) > 100) {

				System.out.println("\nDiscount percentage must be non negative and at most 100.");
			}
			else break;
		}

		customers.add( new Customer( name, Double.parseDouble(discountPercentage) ) );

		System.out.println("New customer created.\n");
	}


	/* Name: createAccount
	 * Purpose: UI to create an account.
	 * Inputs: User inputed customer number and account type.
	 * Side effect: UI prints. Add new account into customer's 'accounts' array list.
	 * Special notes: Multiple helper methods used.
	 */
	private static void createAccount() {
		Customer Customer;
		char 		 accountType;
		int 		 customerNumber;
		String 	 initialDeposit;


		// Calls chooseCustomer (which returns a valid customer number) to pick new account's owner.
		System.out.print("\nCreate account for:");
		customerNumber = chooseCustomer();

		Customer = customers.get(customerNumber);

		System.out.println(		"\nAccount types:\n"
										 	  + "(a) Checking\n"
										 		+ "(b) Savings"				);

		// Infinite loop until 'a' or 'b' is inputed (by calling getMenuChoice) for account type.
		while (true) {
			accountType = getMenuChoice();

			if (accountType != 'a' && accountType != 'b')
				System.out.println("\nEnter \"a\" or \"b\".");
			else break;
		}

		// Infinite loop until valid initial deposit is inputed (calls isPositiveDouble).
		while (true) {
			System.out.print("Initial deposit: ");
			initialDeposit = scan.nextLine();

			if ( !isPositiveDouble(initialDeposit) )
				System.out.println("\nInitial deposit must be non negative.");
			else break;
		}

		// Calls Customer.addAccount to add new account into customer's 'accounts' array list
		Customer.addAccount( accountType, Double.parseDouble(initialDeposit) );

		// UI print:
		// "[Account type] account created for [name], with initial deposit [dep] garlicoins."
		switch (accountType) {
		case 'a': System.out.print("Checking account "); break;
		case 'b': System.out.print("Savings account " ); break;
		}

		System.out.println(	  "created for "
											  + Customer.getName()
												+ ", with initial deposit "
												+ Double.parseDouble(initialDeposit)
												+ " garlicoins.\n"								 );
	}


	/* Name: showBalance
	 * Purpose: UI to show balance of all accounts of chosen customer.
	 * Inputs: User inputed customer number.
	 * Outputs: Customer.getAccountList called in chooseAccount.
	 * Special notes: Multiple helper methods used.
	 */
	private static void showBalance() {
		int customerNumber;

		// chooseCustomer whose balance to show.
		System.out.print("\nShow balance of:");
		customerNumber = chooseCustomer();

		// Back to main menu if inputed customerNumber invalid.
		if ( customerNumber == - 1)
			return;

		System.out.println("");

		// chooseAccount always calls Customer.getAccountList,
		// but doesn't prompt for input when called by showBalance (this method).
		chooseAccount(customerNumber);

		System.out.println("");
	}


	/* Name: deposit
	 * Purpose: UI to deposit.
	 * Inputs: User inputed amount, account index, customer number.
	 * Side effects: UI prints. Account balance increased by inputed amount.
	 * Special notes: Multiple helper methods used.
	 */
	private static void deposit() {
		int 		 customerNumber;
		int 		 account;

		double 	 amount;
		Customer Customer;


		// chooseCustomer whose account will receive the deposit.
		System.out.print("\nChoose customer: ");
		customerNumber = chooseCustomer();

		System.out.println("");

		if ( customerNumber == -1 ) return;

		Customer = customers.get( customerNumber );
		account	 = chooseAccount(customerNumber);
		amount	 = chooseAmount();

		// Calls Customer.deposit, and not Account.deposit for protection.
		Customer.deposit(account, amount);

		System.out.println("Deposit succesful.\n");
	}


	/* Name: withdraw
	 * Purpose: UI to withdraw.
	 * Inputs: User inputed amount, account index, customer number.
	 * Side effects: UI prints. Account balance decreased by inputed amount.
	 * Special notes: Multiple helper methods used.
	 */
	private static void withdrawal() {
		int customerNumber;
		int account;

		double	 amount;
		Customer Customer;


		// chooseCustomer whose account will be target for withdrawal.
		System.out.print("\nChoose customer: ");
		customerNumber = chooseCustomer();

		System.out.println("");

		if ( customerNumber == -1 ) return;

		Customer = customers.get( customerNumber );
		account  = chooseAccount( customerNumber );
		amount 	 = chooseAmount();

		// Calls Customer.withdraw, and not Account.withdraw for protection.
		Customer.withdraw(account, amount);

		System.out.println("Withdrawal successful.\n");
	}


	/* Name: transfer
	 * Purpose: UI to transfer.
	 * Inputs: User inputed customer number, account indexes (source and destination), amount.
	 * Side effects: UI prints. Withdraw from source and deposit in destination by amount.
	 * Special notes: Multiple helper methods used.
	 */
	private static void transfer() {
		int customerNumber;
		int srcAccount;
		int dstAccount;

		double 	 amount;
		Customer Customer;


		customerNumber = chooseCustomer();

		if ( customerNumber == -1 ) return;

		Customer = customers.get(customerNumber);


		// Back to main menu if customer only has 1 account.
		if ( Customer.getAccountCount() == 1 ) {
			System.out.println("This customer only has 1 account.");
			return;
		}

		System.out.println("\nChoose source account.");
		srcAccount = chooseAccount(customerNumber);

		System.out.println("\nChoose destination account.");
		dstAccount = chooseAccount(customerNumber);

		// Back to main menu if trying to source and destination accounts have same index.
		if ( srcAccount == dstAccount ) {
			System.out.println("Can't transfer to same account.\n");
			return;
		}

		amount = chooseAmount();

		// Back to main menu if amount exceeds balance of source account.
		if ( !Customer.transfer(amount, srcAccount, dstAccount) ) {
			System.out.println("Amount exceeds balance.\n");
			return;
		}

		System.out.println("Transfer successful.\n");
	}


	/* Name: chooseCustomer
	 * Purpose: UI to choose customer by customer number.
	 * Inputs: String 'customerNumber'.
	 * Outputs: Above converted into int if valid.
	 * Special notes: Uses helper method isPositiveInteger to verify input.
	 */
	private static int chooseCustomer() {
		String	 callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName();

		String 	 customerNumber;
		Customer Customer;


		System.out.println("");

		// Infinite loop until valid customer number is inputed.
		while (true) {
			for ( int i = 0; i < customers.size(); i++ )
				System.out.println( customers.get(i) );

			System.out.print("Enter customer number: ");
			customerNumber = scan.nextLine();


			// Customer number invalid if is
			// - blank
			// - negative
			// - has 0 as first char if length >= 2 ("00" is invalid)
			// - greater than the customer count (meaning this customer number isn't used yet)
			if ( 		 customerNumber.length() == 0
					 || !isPositiveInteger(customerNumber)
					 || (customerNumber.length() >= 2     && customerNumber.charAt(0) == '0')
					 ||  Integer.parseInt(customerNumber) >= customers.size()							  ) {

				System.out.println("Invalid customer number.\n");
			}

			else {
				Customer = customers.get( Integer.parseInt(customerNumber) );

				// If user is doing something other than creating a new account (depositing, withdrawing,
				// transferring, ...) and the customer currently has no accounts, method returns -1.

				if ( !callerMethod.equals("createAccount") && Customer.getAccountCount() == 0 ) {

					System.out.println("This customer has no accounts.\n");
					return -1;
				}

				// Method returns customerNumber as int
				return Integer.parseInt(customerNumber);
			}
		}
	}


	/* Name: chooseAccount
	 * Purpose: UI to choose from a customer's accounts.
	 * Inputs: int 'customerNumber' as argument. Inputed account index.
	 * Outputs: account index in customer's 'accounts' array list as int.
	 * Special notes:
				Uses helper method isPositiveInteger to verify input.

				Account indexes start at 0 for the array list, but at 1 for sake of UX.
				Thus a valid input = 'valid index + 1'.
				Method thus returns 'valid input - 1' = 'valid index + 1 - 1' = valid index.

				If caller is showBalance, method only shows account list, doesn't ask for input,
				returns -1.
	 */
	private static int chooseAccount(int customerNumber) {
		String 	 callerMethod = Thread.currentThread().getStackTrace()[2].getMethodName();
		Customer Customer 		= customers.get( customerNumber );

		String account;


		// Infinite loop until valid account index is inputed.
		while (true) {
			// Pretty print of account list (balances included).
			System.out.print( Customer.getAccountList() );

			// If caller method is "showBalance", then work is done. Returns -1.
			if ( callerMethod.equals("showBalance") )
				return -1;


			System.out.print("Enter account index: ");
			account = scan.nextLine();

			// Input invalid if is
			// - blank
			// - negative
			// - 0 (for UI/UX). For array list index 0, input 1)
			// - greater than customer total number of account (account not yet created)
			if ( 		 account.length() == 0
					 || !isPositiveInteger(account)
					 ||  Integer.parseInt(account) == 0
					 ||  Integer.parseInt(account)  > Customer.getAccountCount() ) {

				System.out.println("No account of this index.\n");
			}
			else
				// Returns input - 1 as int (for array list indexing).
				return Integer.parseInt(account) - 1;
		}
	}


	/* Name: chooseAmount
	 * Purpose: UI to choose amount (deposit, withdraw, transfer, ...).
	 * Inputs: Inputed amount as String.
	 * Outputs: Valid double 'amount'.
	 * Special notes: Uses helper method isPositiveDouble to verify input.
	 */
	private static double chooseAmount() {
		String amount;

		// Infinite loop until valid amount is inputed.
		while (true) {
			System.out.print("Choose amount: ");

			amount = scan.nextLine();

			// Input invalid if <= 0.
			if ( !isPositiveDouble(amount) || Double.parseDouble(amount) == 0 )
				System.out.println("Amount must be strictly positive.");
			else
				break;
		}

		// Returns 'amount' as double
		return Double.parseDouble(amount);
	}


	/* Name: getMenuChoice
	 * Purpose: UI for letter options (menu, account type to create).
	 * Inputs: String input.
	 * Outputs: Input as char.
	 * Special notes:
				Input valid if length is 1.
				More specific restrictions are in main method.
	 */
	private static char getMenuChoice() {
		String input;

		while (true) {
			System.out.print("Your choice (one letter): ");
			input = scan.nextLine();

			if ( input.length() == 1 ) break;
		}

		return input.charAt(0);
	}


	/* Name: isPositiveInteger
	 * Purpose: Helper. Check if String input is a positive integer.
	 * Inputs: String 'input' as argument.
	 * Outputs: Boolean. True if every character of input is numeric.
	 * Special notes: 0 (or 000000) is valid.
	 */
	private static boolean isPositiveInteger(String input) {

		// Iterates through String to check if chars are numeric.
		for ( int i = 0; i < input.length(); i++ ) {
			if ( !Character.isDigit( input.charAt(i) ) ) {
				return false;
			}
		}

		return true;
	}


	/* Name: isPositiveDouble
	 * Purpose: Helper. Check if String input is a positive double.
	 * Inputs: String 'input' as argument.
	 * Outputs: Boolean. True if input is of form '[digits].[digits]'.
	 * Special notes: Uses regex.
	 */
	private static boolean isPositiveDouble(String input) {
		 return input.matches("\\d+(\\.\\d+)?");
	}


	/* Name: displayMenu
	 * Purpose: UI. Print main menu.
	 */
	private static void displayMenu() {
		System.out.println(		"(a) Create customer\n"
										 		+ "(b) Create bank account\n"
										 		+ "(c) Get balance\n"
										 		+ "(d) Deposit\n"
										 		+ "(e) Withdrawal\n"
										 		+ "(f) Make a tranfer\n"
										 		+ "(g) Quit"									);
	}

	/* Name: main
	 * Purpose: UI. Call methods to do whatever a bank does.
	 * Inputs: char input through getMenuChoice.
	 * Special notes:
				Is infinite loop until user quits.
				Any choice beside a-g is invalid and triggers loop.
				First choice has to be to create a customer (or quit).
	 */
	public static void main(String[] args) {
		char choice;

		// Infinite loop.
		while(true) {
			displayMenu();
			choice = getMenuChoice();

			// If letter choice not to create new customer nor to quit
			if ( choice != 'a' && choice != 'g' ) {

				// and outside valid range, then triggers loop.
				if ( choice < 'a' || choice > 'g' ) {
					System.out.println("\nInvalid choice.");
					continue;
				}

				// If was inside valid range, but 0 customer's been created, also triggers loop.
				else if ( customers.size() == 0 ) {
					System.out.println("\nNeed to create customers first.");
					continue;
				}
			}

			// If a customer's already been created, and letter choice was invalid,
			// then methods will be called.
			switch (choice) {
			case 'a': createCustomer(); break;
			case 'b': createAccount();  break;
			case 'c': showBalance(); 		break;
			case 'd': deposit(); 				break;
			case 'e': withdrawal(); 		break;
			case 'f': transfer(); 			break;
			}

			// Program ends if choice is 'g'.
			if (choice == 'g') {
				System.out.println("");
				break;
			}
		}
	}

}
