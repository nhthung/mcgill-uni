import java.util.Scanner;

public class Ceasar{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    String message = scan.nextLine();
    for(int i = 0; i < message.length(); i++){
      if(65 <= (int)message.charAt(i) && (int)message.charAt(i) <= 90)
        System.out.print((char)(((int)message.charAt(i) - 62)%26 + 65));
      else if(97 <= (int)message.charAt(i) && (int)message.charAt(i) <= 122)
        System.out.print((char)(((int)message.charAt(i) - 94)%26 + 97));
      else
        System.out.print(" ");
    }
  }
}