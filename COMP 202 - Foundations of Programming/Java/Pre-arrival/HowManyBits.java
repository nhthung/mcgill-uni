import java.util.Scanner;
public class HowManyBits{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println("How many values? ");
    int x = scan.nextInt();
    System.out.println("Need " + Math.ceil(Math.log(x)/Math.log(2)) + " bits.");
  }
}
