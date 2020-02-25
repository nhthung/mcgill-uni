import junit.framework.TestCase;
import java.util.Scanner;
public class FahrenheitToCelsius2{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.println("Temperature in Fahrenheit?");
    int deg = scan.nextInt();
    System.out.println((deg - 32) * 5 / 9);
  }
}