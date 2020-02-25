import java.util.*;

public class Sandbox6{
  public static void main(String[] args){
    ArrayList<String> list = new ArrayList<String>();
    list.add("Inigo");
    list.add("Montoya");
    System.out.println(list.get(1));
    list.add(1, "eats shit");
    System.out.println(list.get(1));
    System.out.println(list.get(2));
    list.remove("eats shit");
    System.out.println(list.get(1));
    System.out.println(list.get(2));
  }
}