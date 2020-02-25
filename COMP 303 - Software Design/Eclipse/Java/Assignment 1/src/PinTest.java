/* ======================================================================================================
 * File Name: PinTest.java
 * ======================================================================================================
 * Developer: Le Nhat Hung
 * Purpose: Test correctness of Pin.java.
 * Inputs: Test ranges are
	- Valid PINs in 100 cases: [00, ..., 99]
	- One-digit PINs in 95 cases: all printable ASCII characters
	- Random PINs of length 3-20 in 100 cases
	- Random strictly alphanumeric PINs of length 3-20 in 100 cases
	- Numeric PINs outside of valid range in 19901 cases: [-10000, -1] & [100, 10000]
 * Outputs: Failed test values, and success count for each and all methods.
 *
 * ======================================================================================================
 * Modifications
 * ======================================================================================================
 * January 15 2018 - Starting out. This won't take forever.
 * January 19 2018 - Done. The circle is complete. Wham. Bam. Thank you. Ma'am.
 * January 20 2018 - Polished the text formatting for viewing in Atom.
 */


import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class PinTest {
	static Pin pin = new Pin();

	static int testerSuccessCount = 0;

	// All alphanumeric characters and printable ASCII characters
	static final String alphanum	= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"																			 ,
												 ascii	= " !\"#$%&\\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";

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



	/* Name: checkPinIsCorrectTest
	 * Purpose: Force feed Pin.java's checkPinIsCorrect method values of all test ranges.
	 * Inputs:
		For each valid PIN set as the "current PIN",
		checkPinIsCorrect takes in all values of the test ranges.

	 * Outputs:
		Failed cases and test summary in % of tests passed.
		Pass if truth values of boolean checkPinIsCorrect(inputPin)
		and inputPin.equals(currentPin) identical.
		Else fail.

	 * Side-effects:
		Because checkPinIsCorrect will be tested for every single valid PIN set as the "current PIN",
		by the end of the method, the current set PIN will be '99'
	 */
	static void checkPinIsCorrectTest() {
		int methodSuccessCount = 0;

		System.out.print  ("Tested method: checkPinIsCorrect(String inputPin)\n");

		System.out.println("For each valid PIN set as the \"current PIN\", " +
											 "20296 cases in all the above ranges will be tested:\n");

		for( String currentPin: validPins ) {
			pin.setPin( currentPin );

			// For each valid PIN value set as the "current PIN", the 5 ranges (in 5 arrays) will be tested.
			// test == 0 for 1st test, test == 1 for 2nd test, etc.
			for ( int test = 0; test <= 4; test++ ) {

				// allRanges is the array that holds all of the test range arrays.
				// The variable 'test' acts as an index for allRanges.
				for (String inputPin: allRanges[test]) {

					// Pass if truth values of boolean checkPinIsCorrect(inputPin)
					// and inputPin.equals(currentPin) identical.
					if
					( ( pin.checkPinIsCorrect(inputPin) && inputPin.equals(currentPin) ) ||
					 ( !pin.checkPinIsCorrect(inputPin) && !inputPin.equals(currentPin) ) ) {

						methodSuccessCount++;
						testerSuccessCount++;
					}

					else
						System.out.println("Current PIN is " + currentPin + "\n"							+
															 "Arguments: " + inputPin + " Result: Test failed.");
				}
			}
		}

		// Test summary in percentage of tests passed.
		System.out.println("Test summary: "																+
											 methodSuccessCount															+
											 " out of 2029600 passed.\n"										+
											 "Method checkPinIsCorrect passed "							+
											 (float)methodSuccessCount/2029600*100					+
											 "% of tests.\n"																+
											 "==========================================\n");
	}



	/* Name: setPinTest
	 * Purpose: Gently feed Pin.java's setPin method with values of all test ranges.
	 * Inputs: setPin takes all values of all 5 test ranges.
	 * Outputs:
		Failed cases and test summary in % of tests passed.
		Pass if pinIsValid(inputPin) true for all values of valid range (except the "current PIN").
		Fail otherwise.

	 * Special notes: The method also tests Pin.java's pinIsValid method, since pinIsValid is used in setPin
	 */
	static void setPinTest() {
		int methodSuccessCount = 0,
				testSuccessCount 	 = 0;

		System.out.print("Tested method: setPin(String inputPin)\n");

		// Again the 5 ranges are tested. test == 0 for 1st test, etc.
		for ( int test = 0; test <= 4; test++ ) {
			testSuccessCount = 0;

			// Pretty print of each test range depending on value of 'test' variable
			System.out.print("Now testing ");
			switch (test) {
				case 0: System.out.println("valid PINs in 100 cases: [00, ..., 99]");																					 break;
				case 1: System.out.println("one-digit PINs in 95 cases: all printable ASCII characters");											 break;
				case 2: System.out.println("random PINs of length 3-20 in 100 cases:");																				 break;
				case 3: System.out.println("random strictly alphanumeric PINs of length 3-20 in 100 cases:");									 break;
				case 4: System.out.println("numeric PINs outside of valid range in 19901 cases: [-10000, -1] & [100, 10000]"); break;
			}

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
				if
				(( test == 0 && pin.setPin(inputPin) ) ||
					( test > 0 && !pin.setPin(inputPin) )) {
					testSuccessCount++;
					methodSuccessCount++;
					testerSuccessCount++;
				}
				else {
					System.out.println("Arguments: " + inputPin + " Result: Invalid PIN.");

					if ( inputPin.equals( pin.getPin() ) ) {
						testSuccessCount++;
						methodSuccessCount++;
						testerSuccessCount++;

						System.out.println("\""+ inputPin + "\" as expected isn't valid, " +
															 "as it's identical to the current PIN: Test passed.");
					}
					else
						System.out.println("Test failed.");
				}
			}

			// Print [this many] of [this many] tests passed.
			System.out.print("Test summary: " + testSuccessCount + " out of ");
			switch (test) {
				case 0:
				case 2:
				case 3: System.out.println("100 tests passed.\n");	 break;
				case 1: System.out.println("95 tests passed.\n");		 break;
				case 4: System.out.println("19901 tests passed.\n"); break;
			}
		}


		// Print "Method setPin passed [percentage]% of tests.
		System.out.println("Method setPin passed " +
											 (float)methodSuccessCount/20296*100 +
											 "% of tests.\n" +
											 "==========================================\n");
	}


	/* Name: generateTestRanges
	 * Purpose: Explain all the ranges tested, then generate 5 ranges of:
		- Valid PINs in 100 cases: [00, ..., 99]
		- One-digit PINs in 95 cases: all printable ASCII characters
		- Random PINs of length 3-20 in 100 cases
		- Random strictly alphanumeric PINs of length 3-20 in 100 cases
		- Numeric PINs outside of valid range in 19901 cases: [-10000, -1] & [100, 10000]

	 * Outputs: Pretty explanations.
	 * Side-effects: Method pretty prints the tester program's introduction.
	 * Special notes:
		Method uses imported library java.util.concurrent.ThreadLocalRandom
		and the final Strings 'alphanum' and 'ascii' to generate random PINs.
	 */
	static void generateTestRanges() {

		// Pretty printing all of the test ranges.
		System.out.println("The test ranges for Pin.java are as follow:\n" +
											 "- Valid PINs in 100 cases: [00, ..., 99]\n" +
											 "- One-digit PINs in 95 cases: all printable ASCII characters\n" +
											 "- Random PINs of length 3-20 in 100 cases:\n" +
											 "- Random strictly alphanumeric PINs of length 3-20 in 100 cases:\n" +
											 "- Numeric PINs outside of valid range in 19901 cases: [-10000, -1] & [100, 10000]\n\n" +
											 "==========================================\n");


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


	/* Name: randomString
	 * Purpose: Helper method to return random PINs.
	 * Inputs: int length of desired String, and boolean asciiSwitch: true for ASCII, false otherwise.
	 * Outputs: random String of either strictly alphanumeric or printable ASCII characters.
	 * Special notes:
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


	/* Name: main
	 * Purpose: Call all other methods.
	 * Outputs: Summary with % of tests passed.
	 */
	public static void main(String[] args) {
		generateTestRanges();
		checkPinIsCorrectTest();
		setPinTest();

		// Prints "The program passed [percentage]% of tests.
		System.out.println("The program passed " + (float)testerSuccessCount/2049896*100 + "% of tests.\n");
	}
}
