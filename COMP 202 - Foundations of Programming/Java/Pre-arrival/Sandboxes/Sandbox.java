import java.util.Scanner;

public class Sandbox{
  public static void main(String[] args){
    String poop = "I am the walrus";
    String poop2 = "I am the egg man ";
    System.out.println(poop.substring(0, 15));
    
    Scanner scan = new Scanner(System.in);
    int grade = scan.nextInt();
    int category = grade/10;
    System.out.println(category);
    
    String word = scan.next();
    System.out.println(word + "\n");
    
    System.out.println();
    int number = scan.nextInt();
    System.out.println(reverse(number));
    
    System.out.println(foo(3 == 3));
    
    int[] arr = {1, 2, 3, 4, 5};
    for(int i = 0; i < arr.length - 1; i++)
      System.out.print(arr[i] + ", ");
    System.out.println(arr[arr.length - 1]);
    
    int[] arr2 = new int[arr.length];
    for(int i = 0; i < arr.length; i++)
      arr2[i] = arr[i]*2;
    
    for(int i = 0; i < arr2.length - 1; i++)
      System.out.print(arr2[i] + ", ");
    System.out.println(arr2[arr2.length - 1]);
  }
  
  public static int reverse(int number){
    int reversed = 0;
    do{
      int lastDigit = number%10;
      reversed = reversed*10 + lastDigit;
      number /= 10;
    } while(number > 0);
    return reversed;
  }
  
  public static int foo(boolean y) {
    if (y)
      return 3;
    else
      return 4;
  }
}