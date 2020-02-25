// File name: PinTest.java


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Test correctness of Pin.java.
 *
 * <p>
 * Test ranges are <p>
	- Valid PINs in 100 cases: [00, ..., 99] <p>
	- One-digit PINs in 95 cases: all printable ASCII characters <p>
	- Random PINs of length 3-20 in 100 cases <p>
	- Random strictly alphanumeric PINs of length 3-20 in 100 cases <p>
	- Numeric PINs outside of valid range in 19901 cases: [-10000, -1] & [100, 10000] <p>

 * <b>Modifications</b><p>
 * January  15 2018 - Starting out. This won't take forever. <p>
 * January  19 2018 - Done. The circle is complete. Wham bam thank you ma'am. <p>
 * January  20 2018 - Polished the text formatting for viewing in Atom. <p>
 * February 21 2018 - Implemented Javadoc and JUnit <p>
 *
 * @author	Le Nhat Hung
 * @version 1.0
 * @since		January 15 2018
 */

public class PinTest {
	static Pin pin = new Pin();

	// All alphanumeric characters and printable ASCII characters
	static final String alphanum	= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"																			 ,
											ascii			= " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

	// All arrays of test ranges
	static String[]
			validPins							 		= new String[100],
			randomPins						 		= new String[100],
			oneDigitPins 					 		= new String[95],
			invalidNumericPins 		 		= new String[19901],
			randomAlphanumericPins 		= new String[100];

	// 2 dimensional array holding all of the above arrays
	static String[][]
			allRanges							 		= { validPins, oneDigitPins, randomPins, randomAlphanumericPins, invalidNumericPins };



	/**
	 * <b>Purpose:</b> test Pin constructor. If pin is created as "00", test passes. <p>
	 * <b>Name:</b> 	 testPin
	 */

	@Test
	public void testPin() {
		Pin tempPin = new Pin();

		assertTrue( tempPin.getPin().equals("00") );
	}

	/**
	 * <b>Purpose:</b> Force feed Pin.java's checkPinIsCorrect method values of all test ranges. <p>
	 * <b>Name:</b> testCheckPinIsCorrect <p>
	 *
	 * <b>Inputs:</b><p>
		For each valid PIN set as the "current PIN",
		checkPinIsCorrect takes in all values of the test ranges. <p>

	 * <b>Outputs:</b> <p>
		Failed cases and test summary in % of tests passed. <p>
		Pass if truth values of boolean checkPinIsCorrect(inputPin)
		and inputPin.equals(currentPin) identical. <p>
		Else fail. <p>

	 * <b>Side-effects:</b> <p>
		Because checkPinIsCorrect will be tested for every single valid PIN set as the "current PIN",
		by the end of the method, the current set PIN will be '99'
	 */

	@Test
	public void testCheckPinIsCorrect() {

		generateTestRanges();

		for( String currentPin: validPins ) {
			pin.setPin( currentPin );

			// For each valid PIN value set as the "current PIN", the 5 ranges (in 5 arrays) will be tested.
			// test == 0 for 1st test, test == 1 for 2nd test, etc.
			for ( int test = 0; test <= 4; test++ ) {

				// allRanges is the array that holds all of the test range arrays.
				// The variable 'test' acts as an index for allRanges.
				for (String inputPin: allRanges[test]) {

					if ( inputPin.equals( pin.getPin() ) )
						assertTrue( pin.checkPinIsCorrect(inputPin) );
					else
						assertFalse( pin.checkPinIsCorrect(inputPin) );
				}
			}
		}
	}



	/**
	 * <b>Purpose:</b> Gently feed Pin.java's setPin method with values of all test ranges. <p>
	 * <b>Name:</b>		 testSetPin <p>
	 * <b>Inputs:</b>  setPin takes all values of all 5 test ranges. <p>
	 * <b>Outputs:</b> <p>
		Failed cases and test summary in % of tests passed. <p>
		Pass if pinIsValid(inputPin) true for all values of valid range (except the "current PIN"). <p>
		Fail otherwise. <p>

	 * <b>Special notes:</b> The method also tests Pin.java's pinIsValid method, since pinIsValid is used in setPin
	 */

	@Test
	public void testSetPin() {

		generateTestRanges();

		// Again the 5 ranges are tested. test == 0 for 1st test, etc.
		for ( int test = 0; test <= 4; test++ ) {

			// Again iterating through allRanges array to test for each range
			for ( String inputPin: allRanges[test] ) {

				// Test passes if setPin(inputPin) is true in the valid range (test == 0), or
				// setPin(inputPin) false in invalid range (test >= 0).
				//
				// If setPin(inputPin) is false (meaning inputPin was rejected)
				// even when testing the valid range(test == 0),
				// but inputPin is identical to the "current set PIN",
				// the test still passes.
				//
				// Otherwise, the test fails.

				if ( !inputPin.equals( pin.getPin() ) ) {

					if (test == 0)
						assertTrue( pin.setPin(inputPin) );
					else
						assertFalse( pin.setPin(inputPin) );
				}

				else
					assertFalse( pin.setPin(inputPin) );
			}
		}
	}


	/**
	 * <b>Purpose:</b> Generate array lists. <p>
		- Valid PINs in 100 cases: [00, ..., 99] <p>
		- One-digit PINs in 95 cases: all printable ASCII characters <p>
		- Random PINs of length 3-20 in 100 cases <p>
		- Random strictly alphanumeric PINs of length 3-20 in 100 cases <p>
		- Numeric PINs outside of valid range in 19901 cases: [-10000, -1] &amp; [100, 10000] <p>

	 * <b>Name:</b> generateTestRanges <p>
	 * <b>Special notes:</b><p>
		Method uses imported library java.util.concurrent.ThreadLocalRandom
		and the final Strings 'alphanum' and 'ascii' to generate random PINs.
	 */
	static void generateTestRanges() {

		// Generating the test ranges arrays. Number of for loops minimized to avoid repeating iterations.
		for (int i = 0; i < 100; i++) {
			int randomLength					= ThreadLocalRandom.current().nextInt(3, 20 + 1); // Random Strings of length 3 to 20 chracters.

			// Generating the valid PINs range.
			validPins[i]							= Integer.toString(i);
			if (i <= 9)
				validPins[i]						= "0" + validPins[i]; // appending '0' to make PINs smaller than 10 into 2 digits PINs.

			// Generating the both random strictly alphanumeric PINs and printable ASCII characters PINs.
			randomAlphanumericPins[i] = randomString( randomLength, false );
			randomPins[i]							= randomString( randomLength, true );		// false switch for alphanumeric, true for ASCII.

			// Generating one-digit PINs of the 95 printable ASCII characters.
			if (i <= 94)
				oneDigitPins[i]					= Character.toString( (char)(i + 32) );

			// Generating PINs from -10000 to -1 and from 100 to 10000.
			invalidNumericPins[i]			= Integer.toString( -(i + 1) );
		}

		// The previous for loop only had 100 iterations.
		// This last for loop fills out the rest of the invalidNumericPins array.
		for (int i = 100; i < 19901; i++) {
			if (i < 10000)
				invalidNumericPins[i]		= Integer.toString( -(i + 1) );
			else
				invalidNumericPins[i]		= Integer.toString( i - 9900 );
		}
	}


	/**
	 * <b>Purpose:</b> Helper method to return random PINs. <p>
	 * <b>Name:</b> 	 randomString <p>
	 * <b>Inputs:</b>  int length of desired String, and boolean asciiSwitch: true for ASCII, false otherwise. <p>
	 * <b>Outputs:</b> random String of either strictly alphanumeric or printable ASCII characters. <p>
	 * <b>Special notes:</b> <p>
		Method uses imported library java.security.SecureRandom
		to generate random PINs.
	 */
	static String randomString( int length, boolean asciiSwitch ) {
		SecureRandom	random = new SecureRandom();
		StringBuilder sb		 = new StringBuilder( length );

		// Generating random Strings.
		// Function iterates through the String 'ascii' if asciiSwitch == true,
		// else iterates through 'alphanum'.
		for ( int i = 0; i < length; i++ ) {

			if ( asciiSwitch == true )
				sb.append( ascii.charAt( random.nextInt( ascii.length() ) ) );
			else
				sb.append( alphanum.charAt( random.nextInt( alphanum.length() ) ) );
		}

		return sb.toString();
	}
}
