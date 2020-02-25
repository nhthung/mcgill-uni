/* ======================================================================================================
 * File Name: Pin.java
 * ======================================================================================================
 * Developer: Le Nhat Hung
 * Purpose: Define Pin class
 * Inputs: PINs as function arguments
 * Outputs: PIN statuses (user input matches old code, valid or invalid, etc.)
 *
 * ======================================================================================================
 * Modifications
 * ======================================================================================================
 * January 15 2018 - Starting out. Not much to document. Wham bam thank you ma'am.
 * January 19 2018 -
		pinIsPositiveInteger method uses try/catch exception.
		Apparently has a lot of overhead.
		Removed method, used regex in pinIsValid method instead.

 * January 19 2018 (later) -
 		Apparently regex is super slow. Changed to isDigit(String) instead.
 */


public class Pin {
	private String currentPin;


	/* Name: Pin
	 * Purpose: Constructor. Initializes currentPin to 00.
	 */
	Pin() { currentPin = "00"; }


	/* Name: checkPinIsCorrect
	 * Purpose: Returns true if inputPin matches currentPin, else returns false.
	 * Inputs: String inputPin
	 * Outputs: boolean
	 */
	boolean checkPinIsCorrect(String inputPin) {
		return inputPin.equals(currentPin);
	}


	/* Name: getPin
	 * Purpose: Getter of currentPin
	 * Outputs: String currentPin
	 */
	String getPin() {
		return currentPin;
	}


	/* Name: setPin
	 * Purpose: Set currentPin as inputPin if inputPin valid
	 * Inputs: String inputPin
	 * Outputs: Returns true on success, else returns false
	 */
	boolean setPin(String inputPin) {
		if( pinIsValid(inputPin) ) {
			currentPin = inputPin;
			return true;
		}

		return false;
	}


	/* Name: pinIsValid
	 * Purpose: Returns true if inputPin:
		- is a positive integer (0 included)
		- has 2 digits
		- and different from currentPin
		else returns false
	 * Inputs: String inputPin as argument
	 * Outputs: boolean
	 */
	private boolean pinIsValid(String inputPin) {
		boolean isInteger = true;

		// Checking if all characters of inputPin is a digit.
		for ( int i = 0; i < inputPin.length(); i++ ) {
			if ( !Character.isDigit( inputPin.charAt(i) ) ) {
				isInteger = false;
				break;
			}
		}

		return
				isInteger &&
				inputPin.length() == 2 &&
				!inputPin.equals(currentPin);
	}
}
