public class PowerOfTwo{
  public static int square(int x){
    if(x == 1)
      return 1;
    else
      return square(x - 1) + 2*x - 1;
  }
  
  public static void main(String[] args){
    System.out.println(square(3));
  }
}