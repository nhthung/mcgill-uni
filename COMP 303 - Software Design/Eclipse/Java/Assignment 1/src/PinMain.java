/* ======================================================================================================
 * File Name: PinMain.java
 * ======================================================================================================
 * Developer: Le Nhat Hung
 * Purpose: Program UI. Ask user for current PIN, then to set a new PIN
 * Inputs: PINs from user, first to check if match with current set pin, then to set a new PIN.
 * Outputs: UI prints.
 * Side-effects: Changes the PIN according to user manipulation, as expected.
 * ======================================================================================================
 * Modifications
 * ======================================================================================================
 * January 15 2018 - Pretty simple. Put together quickly.
 */


import java.util.Scanner;

public class PinMain {
	static Pin pin = new Pin();
	static Scanner scan = new Scanner(System.in);


	public static void main(String[] args) {
		String inputPin;


		// Infinite loop until user enter PIN identical to the current set PIN.
		while(true) {
			System.out.print("Please input your old pin: ");

			if( pin.checkPinIsCorrect( scan.nextLine() ) ) {
				System.out.println("\nOld pin confirmed.\n");
				break;
			}
			else
				System.out.println("\nThat is not your old pin.\n");
		}


		// Infinite loop until user enter a new, valid PIN.
		while(true) {
			System.out.print("Please input your new pin: ");
			inputPin = scan.nextLine();

			if( pin.setPin(inputPin)) {
				System.out.println("\nNew pin confirmed.");
				System.out.println("\nYour pin has been updated to " + inputPin + ".\n");
				break;
			}
			else
				System.out.println("\nInvalid pin.\n");
		}

	}

}
