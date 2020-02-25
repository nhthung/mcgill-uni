import java.util.ArrayList;

public class AlphabetOrder{
  public static boolean check(ArrayList<String> arrlist){
    for(int i = 0; i < arrlist.size() - 1; i++){
      if(arrlist.get(i).compareTo(arrlist.get(i + 1)) > 0){
        System.out.println("Nope");
        return false;
      }
    }
    System.out.println("Yep");
    return true;
  }
  
  public static void main(String[] args){
    ArrayList<String> arrlist = new ArrayList<String>();
    arrlist.add("ABC");
    arrlist.add("DEF");
    check(arrlist);
  }
}