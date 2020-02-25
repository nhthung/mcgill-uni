import java.util.HashSet;

public class HashSetTest{
  public static HashSet<Integer> union(HashSet<Integer> hs1, HashSet<Integer> hs2){
    HashSet<Integer> hs = new HashSet<Integer>();
    for(Integer i: hs1)
      hs.add(i);
    for(Integer i: hs2)
      hs.add(i);
    return hs;
  }
  
  public static void main(String[] args){
    HashSet<Integer> hs1 = new HashSet<Integer>(); hs1.add(1); hs1.add(2);
    HashSet<Integer> hs2 = new HashSet<Integer>(); hs1.add(3); hs1.add(4);
    HashSet<Integer> hs = union(hs1, hs2);
    for(Integer i: hs){
      int primitive = i;
      System.out.print(primitive + " ");
    }
  }
}