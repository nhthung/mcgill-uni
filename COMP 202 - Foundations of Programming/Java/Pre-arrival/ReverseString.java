public class ReverseString{
  public static void main(String[] args){
    System.out.println(reverse(args[0]));
  }
  
  public static String reverse(String s){
    String reversed = "";
    for(int i = 0; i < s.length(); i++)
      reversed = s.charAt(i) + reversed;
    return reversed;
  }
}