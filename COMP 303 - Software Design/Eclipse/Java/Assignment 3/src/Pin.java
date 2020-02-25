// File name: Pin.java

/**
 * Pin defines the Pin object, its accessor, mutator and verifier methods.
 *
 * <p>
 * <b>Modifications:</b>
 * <p>
 * January  15 2018 - Starting out. Not much to document. <p>
 * January  19 2018 -
		pinIsPositiveInteger method uses try/catch exception.
		Apparently has a lot of overhead.
		Removed method, used regex in pinIsValid method instead. <p>

 * January  19 2018 (later) -
 		Apparently regex is super slow. Changed to isDigit(String) instead. <p>
 		
 * February 21 2018 - Implemented Javadoc and JUnit
 * 
 * <p>
 * @author	Le Nhat Hung
 * @version 1.0
 * @since		January 15 2018
 */


public class Pin {
	private String currentPin;


	/**
	 * Purpose: Constructor. Initializes currentPin to 00. <p>
	 * Name:		Pin
	 */

	Pin() { currentPin = "00"; }


	/**
	 * Purpose: Set currentPin as inputPin if inputPin valid <p>
	 * Name:		setPin
	 *
	 * @param  inputPin String
	 * @return boolean. True on success, else returns false.
	 */

	boolean setPin(String inputPin) {
		if( pinIsValid(inputPin) ) {
			currentPin = inputPin;
			return true;
		}

		return false;
	}


	/**
	 * Purpose: Getter of currentPin <p>
	 * Name:		getPin
	 *
	 * @return currentPin
	 */

	String getPin() {
		return currentPin;
	}


	/**
	 * Purpose: Returns true if inputPin matches currentPin, else returns false. <p>
	 * Name:		checkPinIsCorrect
	 *
	 * @param	inputPin String
	 * @return boolean
	 */

	boolean checkPinIsCorrect(String inputPin) {
		return inputPin.equals(currentPin);
	}


	/**
	 * Purpose: Returns true if inputPin valid. <p> Meaning: <p>
		- is a positive integer (0 included) <p>
		- has 2 digits <p>
		- and different from currentPin <p>
		else returns false. <p>

	 * Name: 	pinIsValid
	 *
	 * @param  inputPin String
	 * @return boolean
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
