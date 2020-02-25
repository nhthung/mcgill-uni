public class COMP202{
  public static void main(String[] args){
    System.out.println((byte)129);
  }
  
  public static double log(double x, double y){
  //Keywords (public static), return type (double), method signature
    return Math.log(y)/Math.log(x);
  }
  
  public static String reverse(String input){
    char[] in = input.toCharArray();
    int begin = 0;
    int end = in.length - 1;
    char temp;
    while(end > begin){
        temp = in[begin];
        in[begin] = in[end];
        in[end] = temp;
        end--;
        begin++;
    }
    return new String(in);
  }
  
  public static boolean checkifreversed(String s1, String s2){
    if(s1.equals(reverse(s2))){
      return true;
    } else{
      return false;
    }
  }
  
  public static void sayGreetings(String s1, String s2){
    System.out.print(s1 + " says hello to " + s2 + ".");
  }
  
  public static double volsphere(double r){
    return (4.0/3.0)*Math.PI*cube(r);
  }
  
  public static double cube(double a){
    int counter = 0;
    double cubed = a;
    while(counter < 2){
      cubed *= a;
      counter += 1;
    }
    return cubed;
  }
  
  public static double die6(){
    return Math.ceil((Math.random()*6)+1);
  }
  
  public static boolean isDivisible(int n, int m){
    return n%m == 0;
  }
  
  public static boolean isTriangle(int a, int b, int c){
    return (a <= b+c) && (b <= a+c) && (c <= a+b);
  }
  
  public static boolean prefixSuffix(String word, String prefix, String suffix){
    return word.startsWith(prefix) && word.endsWith(suffix);
  }
  
}