/* ==============================================================
 * COMP 307 - Principles of Web Development
 * 
 * Assignment 2 - Cryptography
 * Student ID: 260793376
 * 
 * ==============================================================
 * File Name: MyDES.java
 * ==============================================================
 * Developer: Le Nhat Hung
 * Purpose: Block cipher encryption
 * Inputs: A 3-integer key and a string message
 * Outputs: Encrypted message + decrypted message (not hardcoded)
 *
 */

import java.util.Scanner;

public class MyDES {
	
	/* Defining environment variables */
	
	private static final int
		WELCOME_PROMPT 			= 0,
		KEY_PROMPT 				= 1,
		MESSAGE_PROMPT 			= 2,
		ENCRYPTED_MESSAGE_PROMPT = 3,
		DECRYPTED_MESSAGE_PROMPT = 4,
		END_PROMPT 				= 5,
		
		INVALID_KEY				= 0,
		VALID_KEY					= 1,
		
		INVALID_MESSAGE			= 2,
		VALID_MESSAGE				= 3,
		
		ENCRYPTED_MESSAGE			= 0,
		DECRYPTED_MESSAGE			= 1,
		
		KEY_SIZE					= 3,
		KEY_INDEX_ZERO				= 0,
		KEY_INDEX_ONE				= 1,
		KEY_INDEX_TWO				= 2,
		
		SHIFT_RIGHT				= 0,
		SHIFT_LEFT					= 1,
		
		/* The range will be all printable ASCII characters
		 * with [SPACE] included */
		
		ASCII_RANGE				= 95,
		ASCII_RANGE_LOWER			= 32,
		ASCII_RANGE_UPPER			= 126;
	
	private static int
		MESSAGE_LENGTH = 0;
		
	private static final
		String[] RESPONSES =
		{"You did not input 3 positive integer numbers each strictly >9 and <100\n",
		 "Your key is valid.\n",
		 "You input an empty string\n",
		 "Your message is valid.\n"};
	
	private static final
		String[] PROMPTS =
		{"Welcome to myDES\n",
		 "Please enter your key (3 numbers, press enter after each one):\n",
		 "Please enter your message: ",
		 "Encrypted message: ",
		 "Decrypted message: ",
		 "End of myDES\n"};
	
	private static int[]
		key;
	
	private static String
		message;
	

	private static char[]
		encryptedMessage,
		decryptedMessage;
	
	
	private static
		Scanner scanner = new Scanner(System.in);
	
	
	/* Functions start here */
	
	
	/* Name: printResponse
	 * Purpose: Print responses to valid/invalid inputs
	 */
	private static void printResponse(int response) {
		System.out.print( RESPONSES[response] );
	}
	
	/* Name: printPrompt
	 * Purpose: Print welcoming and input prompts
	 */
	private static void printPrompt(int prompt) {
		System.out.print( PROMPTS[prompt] );
	}
	
	/* Name: printMessage
	 * Purpose: Print encryptedMessage or decryptedMessage as a string
	 */
	private static void printMessage(int msg) {
		char[] msgArray;
		
		if (msg == ENCRYPTED_MESSAGE)
			msgArray = getEncryptedMessage();
		else
			msgArray = getDecryptedMessage();
		
		for (int i = 0; i < MESSAGE_LENGTH; i++) {
			System.out.print(msgArray[i]);
		}
		
		System.out.println();
	}
	
	/* Name: setKey
	 * Purpose: Set the key's chosen cell/index to a number
	 * Inputs: int num, index
	 */
	private static void setKey(int num, int index) {
		key[index] = num;
	}
	
	/* Name: getKeyNum
	 * Purpose: Return content of key's chosen cell
	 * Inputs: int num
	 */
	private static int getKeyNum(int index) {
		return key[index];
	}
	
	/* Name: setMessage
	 * Purpose: Set global String message & int MESSAGE_LENGTH
	 * Inputs: String msg
	 */
	private static void setMessage(String msg) {
		message = msg;
		MESSAGE_LENGTH = msg.length();
	}
	
	/* Name: setEncryptedMessage
	 * Purpose: Set global char[] encryptedMessage
	 * Inputs: char[] array
	 */
	private static void setEncryptedMessage(char[] array) {
		encryptedMessage = array;
	}
	
	/* Name: getEncrpytedMessage
	 * Purpose: Return clone of encryptedMessage
	 */
	private static char[] getEncryptedMessage() {
		return encryptedMessage.clone();
	}
	
	/* Name: setDecryptedMessage
	 * Purpose: Set global char[] decryptedMessage
	 * Inputs: char[] array
	 */
	private static void setDecryptedMessage(char[] array) {
		decryptedMessage = array;
	}
	
	/* Name: getDecryptedMessage
	 * Purpose: Return clone of decryptedMessage
	 */
	private static char[] getDecryptedMessage() {
		return decryptedMessage.clone();
	}
	
	/* Name: isValidNum
	 * Purpose: Return true if
	 * 		- keyNum is int
	 * 		- AND 9 < keyNum < 100
	 * 
	 * Inputs: String keyNum
	 */
	private static boolean isValidNum(String keyNum) {
		try {
			Integer.parseInt(keyNum);
		}
		catch (NumberFormatException nfe){
			return false;
		}
		
		int num = Integer.parseInt(keyNum);
		
		return num > 9 &&
				num < 100;
	}
	
	/* Name: isValidMessage
	 * Purpose: Return true if
	 * 		- msg != null and msg length != 0
	 * 		- AND all chars of msg is
	 * 			inside the permitted ASCII range
	 * 
	 * Inputs: String msg
	 */
	private static boolean isValidMessage(String msg) {
		int len, asciiValue;
		char c;
		
		len = msg.length();
		
		if (msg == null || len == 0)
			return false;
		
		for (int i = 0; i < len; i++) {
			c = msg.charAt(i);
			asciiValue = (int)c;
			
			if (asciiValue < ASCII_RANGE_LOWER ||
				asciiValue > ASCII_RANGE_UPPER)
				return false;
		}
		
		return true;
	}
	
	/* Name: inputKey
	 * Purpose: Prompt for and take
	 * 			user input of 3 integers for the key
	 */
	private static void inputKey() {
		int num;
		String keyNum;
		
		scanner = new Scanner(System.in);
		key = new int[3];
		
		printPrompt(KEY_PROMPT);
		
		for (int i = 0; i < KEY_SIZE; i++) {
			keyNum = scanner.nextLine();
			
			if (!isValidNum(keyNum)) {
				printResponse(INVALID_KEY);
				printPrompt(END_PROMPT);
				System.exit(0);
			}
			
			num = Integer.parseInt(keyNum);
			setKey(num, i);
		}
		
		printResponse(VALID_KEY);
	}

	/* Name: inputMessage
	 * Purpose: Prompt for and take
	 * 			user input of message to be encrypted
	 */
	private static void inputMessage() {
		String input;
		
		scanner = new Scanner(System.in);
		
		printPrompt(MESSAGE_PROMPT);
		
		input = scanner.nextLine();
		
		if (!isValidMessage(input)) {
			printResponse(INVALID_MESSAGE);
			printPrompt(END_PROMPT);
			System.exit(1);
		}
		
		setMessage(input);
		printResponse(VALID_MESSAGE);
	}
	
	/* Name: toCharArray
	 * Purpose: Return String msg in form of char[]
	 * Inputs: String msg
	 */
	private static char[] toCharArray(String msg) {
		int len = msg.length();
		char array[] = new char[len];
		
		for (int i = 0; i < len; i++) {
			array[i] = msg.charAt(i);
		}
		
		return array;
	}
	
	/* Name: shiftChar
	 * Purpose: shift char c by shiftVal in chosen direction
	 * 		- Right when encrypting
	 * 		- Left when decrypting
	 * 
	 * Inputs: char c, int shiftVal, int direction
	 */
	private static char shiftChar(char c, int shiftVal, int direction) {
		int initialAscii, encryptedAscii;
		char encryptedChar;
		
		initialAscii = (int)c;
		
		/* Right Caesar shift, for encryption */
		if (direction == SHIFT_RIGHT) {
			encryptedAscii =
				(initialAscii - ASCII_RANGE_LOWER + shiftVal)
				% ASCII_RANGE
				+ ASCII_RANGE_LOWER;
		}
		
		/* Left Caesar shift, for decryption */
		else {
			encryptedAscii =
				((initialAscii - ASCII_RANGE_LOWER - shiftVal)
				% ASCII_RANGE + ASCII_RANGE)
				% ASCII_RANGE
				+ ASCII_RANGE_LOWER;
		}
		
		encryptedChar = (char)encryptedAscii;
		
		return encryptedChar;
	}
	
	/* Name: blockCipher
	 * Purpose: Apply block cipher to a char array then return it.
	 * 			The int direction is for shiftChar() which is used here
	 * 
	 * Inputs: char[] array, int direction
	 */
	private static char[] blockCipher(char[] array, int direction) {
		int len, keyIndex;
		char encryptedChar;
		char[] cipher;
		
		len = array.length;
		cipher = new char[len];
		
		for (int i = 0; i < len; i++) {
			keyIndex = i % KEY_SIZE;
			encryptedChar =
				shiftChar(
					array[i],
					getKeyNum(keyIndex),
					direction
				);
			cipher[i] = encryptedChar;
		}
		
		return cipher;
	}
	
	/* Name: rotateRight
	 * Purpose: Rotate the global int[] key to the right
	 */
	private static void rotateRight() {
		int temp = getKeyNum(KEY_INDEX_TWO);
		
		setKey(getKeyNum(KEY_INDEX_ONE), KEY_INDEX_TWO);
		setKey(getKeyNum(KEY_INDEX_ZERO), KEY_INDEX_ONE);
		setKey(temp, KEY_INDEX_ZERO);
	}
	
	/* Name: rotateLeft
	 * Purpose: Rotate the global int[] key to the left
	 */
	private static void rotateLeft() {
		int temp = getKeyNum(KEY_INDEX_ZERO);
		
		setKey(getKeyNum(KEY_INDEX_ONE), KEY_INDEX_ZERO);
		setKey(getKeyNum(KEY_INDEX_TWO), KEY_INDEX_ONE);
		setKey(temp, KEY_INDEX_TWO);
	}
	
	/* Name: encrypt
	 * Purpose: Encrypt the user inputed message
	 * Notes: Assign the resulting array to
	 * 			the global char[] encryptedMessage
	 */
	private static void encrypt() {
		char[]
			array,
			array2,
			array3,
			array4;
		
		array = toCharArray(message);
		
		array2 = blockCipher(array, SHIFT_RIGHT);
		rotateRight();
		array3 = blockCipher(array2, SHIFT_RIGHT);
		rotateRight();
		array4 = blockCipher(array3, SHIFT_RIGHT);
		
		setEncryptedMessage(array4);
	}
	
	/* Name: decrypt
	 * Purpose: Decrypt the global char[] encryptedMessage
	 * Notes: Assign the result
	 * 			to the global char[] decryptedMessage
	 */
	private static void decrypt() {
		char[]
			array,
			array2,
			array3,
			array4;
		
		array4 = getEncryptedMessage();
		
		array3 = blockCipher(array4, SHIFT_LEFT);
		rotateLeft();
		array2 = blockCipher(array3, SHIFT_LEFT);
		rotateLeft();
		array = blockCipher(array2, SHIFT_LEFT);
		
		setDecryptedMessage(array);
	}
	
	
	/* Main program starts here */
	
	public static void main(String[] args) {
		printPrompt(WELCOME_PROMPT);
		
		inputKey();
		inputMessage();
		encrypt();
		
		printPrompt(ENCRYPTED_MESSAGE_PROMPT);
		printMessage(ENCRYPTED_MESSAGE);
		
		decrypt();
		
		printPrompt(DECRYPTED_MESSAGE_PROMPT);
		printMessage(DECRYPTED_MESSAGE);
		
		printPrompt(END_PROMPT);
	}

}