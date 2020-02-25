package simulator;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/*------------------------------------------------------------------------------
Main program
--------------------------------------------------------------------------------*/

/**
Generate the world and create the loop

@author		Laetitia Fesselier
@since		April, 9th 2018
@version	1.0
*/
public class Simulator {	
	static World world;
	static Scanner stdIn = new Scanner(System.in);
	
	/*------------------------------------------------------------------------------
		main
	--------------------------------------------------------------------------------*/

	/**
	Prompt the user to iterate again.

	@author		Laetitia Fesselier
	*/
	public static void main(String[] args) {
		buildWorld();
		String answer = "yes";
		while(answer.equals("yes")) {
			for (int i = 0; i < 100; i++) {
				world.step();
			}
			
			System.out.println("Would you like to run the simulation again? (yes/no)");
			boolean failure = true;
			while(failure) {
				try {
					answer = stdIn.nextLine();
				} catch (InputMismatchException e) {
					System.out.println("Error "+ e);
					e.printStackTrace();
				}
				if (answer.equals("yes") || answer.equals("no")) failure = false;
				if (failure) System.out.println("Please answer yes or no");
			} 
		}
		stdIn.close();
		world.close();
	}
	
	/*------------------------------------------------------------------------------
		buildWorld
	--------------------------------------------------------------------------------*/
	
	/**
	Create the map
	
	@author		Laetitia Fesselier
	*/
	
	private static void buildWorld() {		
		world = new World(5, 5);
		randomize(5, 3, 2);
		world.display();
	}
	
	/*------------------------------------------------------------------------------
		randomize
	--------------------------------------------------------------------------------*/
	
	/**
	Generate a random map
	
	@param		int iTotal, int mTotal, int aTotal
	@throws		IllegalArgumentException
	@author		Laetitia Fesselier
	*/
	
	private static void randomize(int iTotal, int mTotal, int aTotal) {
		if(iTotal + mTotal + aTotal > world.getWidth()*world.getHeight())
			throw new IllegalArgumentException("Your numbers of elements is too big for your world.");
			
		int iCount, mCount, aCount;
		Random rand = new Random();
		int x, y;
		
		iCount = 0;
		while(iCount < iTotal) {
			try {
				x = rand.nextInt(world.getWidth());
				y = rand.nextInt(world.getHeight());
				
				world.add(new Immovable("Immovable" + (iCount+1), (char)(48+iCount), x, y));
				iCount++;
			} catch(Exception e) {
			}
		}
		
		mCount = 0;
		while(mCount < mTotal) {
			try {
				x = rand.nextInt(world.getWidth());
				y = rand.nextInt(world.getHeight());
				
				world.add(new Movable("Movable" + (mCount+1), (char)(97+mCount), x, y));
				mCount++;
			} catch(Exception e) {}
		}
		
		aCount = 0;
		while(aCount < aTotal) {
			try {
				x = rand.nextInt(world.getWidth());
				y = rand.nextInt(world.getHeight());
				
				world.add(new Autonomous("Autonomous" + (aCount+1), (char)(65+aCount), x, y));
				aCount++;
			} catch(Exception e) {}
		}
	}
}