public class FizzBuzz{
  public static void main(String[] args){
    int n = 1;
    while(n < 101){
      String fizz = "", buzz = "";
      if( (n%3 != 0) && (n%5 != 0) )
        System.out.println(n);
      else{
        if(n%3 == 0)
          fizz = "Fizz";
        if(n%5 == 0)
          buzz = "Buzz";
        System.out.println(fizz + buzz);
      }
      n++;
    }
  }
}