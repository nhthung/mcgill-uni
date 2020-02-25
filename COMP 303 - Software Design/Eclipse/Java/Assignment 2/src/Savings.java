/* ============================================================
 * File Name: Savings.java
 * ============================================================
 * Developer: Le Nhat Hung
 * Purpose: Define Savings class/object extending Account class
			and its conditional withdrawal + deposit methods.

 * ============================================================
 */

public class Savings extends Account {

	/* Name: Savings
	 * Purpose: Constructor. Call super constructor.
	 * Inputs: double 'amount'.
	 */
	Savings(double amount) {
		super(amount);
	}

	/* Name: savingsDeposit
	 * Purpose: Increase balance by inputed amount + 1

	 * Inputs: double 'amount'.
	 * Outputs: Boolean. Returns Account.deposit(amount + 1).
	 * Special notes:
				Called by method in Customer class, which adds discount %/100
				to user-inputed amount.
				The double 'amount' of savingsDeposit(double amount) (this method)
				is thus always 'user-inputed amount + discount%/100'.
	 */
	boolean savingsDeposit(double amount) {
		return deposit(amount + 1);
	}

	/* Name: savingsWithdraw
	 * Purpose: Decrease balance by inputed amount
				if at least 1000

	 * Inputs: double 'amount'.
	 * Outputs: Boolean. True if 'amount' >= 1000.
	 * Side effects: Prints "Minimal withdrawal from savings accounts is 1000 garlicoins." on false.
	 */
	boolean savingsWithdraw(double amount) {
		if ( amount < 1000 ) {
			System.out.println("Minimal withdrawal from savings accounts is 1000 garlicoins.");
			return false;
		}

		return withdraw(amount);
	}

	/* Name: toString
	 * Purpose: Override Java's toString for UI.
	 * Outputs: String "Savings".
	 */
	public String toString() {
		return "Savings - balance: " + getBalance() + " garlicoins.";
	}
}
