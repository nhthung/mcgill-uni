/* =============================================================
 * File Name: Checking.java
 * =============================================================
 * Developer: Le Nhat Hung
 * Purpose: Define Checking class/object extending Account class
			and its conditional withdrawal method.

 * Special notes: Class specific deposit method's unnecessary.
 * =============================================================
 */

public class Checking extends Account {

	/* Name: Checking
	 * Purpose: Constructor. Call super constructor.
	 * Inputs: double 'amount'.
	 */
	Checking(double amount) { super(amount); }

	/* Name: checkingWithdraw
	 * Purpose: Decrease balance by inputed amount + fee
				if doesn't exceed balance.

	 * Inputs: double 'amount'.
	 * Outputs: Boolean. True if 'amount' + 1 (fee) <= balance.
	 * Side effects: Prints "Amount + fee exceeds balance" on false.
	 */
	boolean checkingWithdraw(double amount) {
		if ( amount + 1 > getBalance() ) {
			System.out.println("Amount + fee exceeds balance.");
			return false;
		}

		return withdraw(amount + 1);
	}

	/* Name: toString
	 * Purpose: Override Java's toString for UI.
	 * Outputs: String "Checking". Crazy.
	 */
	public String toString() {
		return "Checking - balance: " + getBalance() + " garlicoins.";
	}
}
