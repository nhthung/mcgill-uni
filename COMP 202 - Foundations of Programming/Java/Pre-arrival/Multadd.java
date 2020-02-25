public class Multadd{
  public static void main(String[] args){
    System.out.println(multadd(1.0, 2.0, 3.0));
    System.out.println(multadd(Math.cos(Math.PI/4.0), 0.5, Math.sin(Math.PI/4.0)));
    System.out.println(multadd(Math.log10(10.0), 1.0, Math.log10(20.0)));
  }
  
  public static double multadd(double a, double b, double c){
    return a * b + c;
  }
  
  public static double expSum(double x){
    return multadd(x, Math.exp(-x), Math.sqrt(1 - Math.exp(-x)));
  }
}