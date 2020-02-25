public class SumDigits{
  private static int sum = 0;
  
  public static int sumDigits(int n){
    if(n == n%10){
      sum += n;
      return sum;
    }
    else{
      sum += n%10;
      n = (int)(n/10);
      return sumDigits(n);
    }
  }
  
  public static void main(String[] args){
    System.out.println(sumDigits(123));
  }
}