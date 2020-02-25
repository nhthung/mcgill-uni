public class OddSum{
  public static void main(String[] args){
    String n = args[0];
    System.out.println(oddSum(Integer.parseInt(n)));
  }
  
  public static int oddSum(int n){
    if (n == 1 || n == 2){
      return 1;
    } else {
      if (n%2 == 0){
        n -= 1;
      }
      return n + oddSum(n - 1);
    }
  }
}