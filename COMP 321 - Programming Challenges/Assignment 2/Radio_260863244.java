package assignment2;
import java.util.Scanner;

public class Radio_260863244 {
	
	public static int maximumPeriod(int[] arr){
		
		//have a variable that stores the 1st element of the array
        int maxprofit = arr[0];
        int maxcurrent = arr[0];

        for (int i = 1; i < arr.length; i++)
        {
        	//get the max between current element and the next element
        	int next = maxcurrent + arr[i];
            maxcurrent = Math.max(arr[i], next);
            //get the max between the previous profit and the current profit
            maxprofit = Math.max(maxprofit, maxcurrent);

        }
        return maxprofit;
    }
    
    public static void main (String[] args)
    {
        Scanner obj = new Scanner(System.in);

        int breaks = obj.nextInt();
        int price = obj.nextInt();
        
        //create an array for the breaks
        int[] arr = new int[breaks];

        //put the breaks in the array
        for (int i = 0; i < breaks; i++) {
            arr[i] = (obj.nextInt()- price);
        }
        
        int profit = maximumPeriod(arr);
        System.out.println(profit);
        
        obj.close();
    }
}
