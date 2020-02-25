public class TestStringArray{
  public static void main(String[] args){
    StringArray arr = new StringArray();
    arr.add("Cloudless");
    arr.add("you fall");
    System.out.println(arr.get(0));
    System.out.println(arr.get(1));
    arr.add(1, "everyday");
    System.out.println(arr.get(0));
    System.out.println(arr.get(1));
    System.out.println(arr.get(2));
    System.out.println(arr.indexOf("you fall"));
    System.out.println(arr.size());
    arr.remove(2);
    System.out.println(arr.get(1));
  }
}