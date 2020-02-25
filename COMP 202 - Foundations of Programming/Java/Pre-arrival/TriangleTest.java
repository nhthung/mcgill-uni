import java.util.Scanner;

public class TriangleTest{
  public static void main(String[] args){
    Scanner input = new Scanner(System.in);
    System.out.println("Sides of potential triangle?");
    int a = input.nextInt();
    int b = input.nextInt();
    int c = input.nextInt();
    if (isTriangle(a, b, c)){
      System.out.println("It's a triangle, but with unlimited potential");
    } else {
      System.out.println("Take that shit outta here");
    }
  }
  
  public static boolean isTriangle(int a, int b, int c){
    return (a <= b+c) && (b <= a+c) && (c <= a+b);
  }
}