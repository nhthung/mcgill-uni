import java.util.HashSet;

public class SameLetters{
  public static boolean sameLetters(String a, String b){
    HashSet<Character> hs = new HashSet<Character>();
    for(int i = 0; i < a.length(); i++)
      hs.add(a.charAt(i));
    for(int i = 0; i < b.length(); i++){
      if(!hs.contains(b.charAt(i)))
        return false;
    }
    return true;
  }
  
  public static void main(String[] args){
    System.out.println(sameLetters("abc", "eaaabbbccc"));
  }
}