import java.util.Scanner;

public class Primality{
  public static void main(String[] args){
    for(int n = 2; n <= 1000; n++){
      if(isPrime(n))
        System.out.println(n);
    }
  }
  
  public static boolean isPrime(int n){
    int counter = 0;
    if(n == 2 || n == 3)
      return true;
    else{
      for(int divisor = 2; divisor <= (int)Math.sqrt(n); divisor++){
        if(n%divisor == 0)
          counter++;
      }
    }
    if(counter != 0)
      return false;
    else
      return true;
  }
}