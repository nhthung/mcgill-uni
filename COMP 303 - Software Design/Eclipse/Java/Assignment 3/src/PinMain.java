// File name: PinMain.java


import java.util.Scanner;


/**
 * UI. Ask user for current PIN, then to set a new PIN.
 *
 * <p>
 * <b>Modifications:</b>
 * <p>
 * January  15 2018 - Pretty simple. Put together quickly. <p>
 * February 21 2018 - Implemented Javadoc and JUnit <p>
 *
 * @author	 Le Nhat Hung
 * @version  1.0
 * @since		 January 15 2018
 */

public class PinMain {
	static Pin		 pin  = new Pin();
	static Scanner scan = new Scanner(System.in);


	/**
	 * Purpose: Ask user for current PIN, then to set a new PIN.
	 * Name:	main
	 */
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
