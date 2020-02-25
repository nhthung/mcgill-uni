public class Power{
  public static void main(String[] args){
    String x = args[0]; String n = args[1];
    System.out.println(power(Double.parseDouble(x), Integer.parseInt(n)));
  }
  
  public static double power(double x, int n){
    if(n == 0){
      return 1.0;
    } else{
      return x * power(x, n - 1);
    }
  }
}