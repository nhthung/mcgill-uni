public class MultTable{
  public static void main(String[] args){
    int z = 1;
    for(int x = 1; x <= 9; x++){
      for(int y = z; y <= 9; y++){
        System.out.print(x + "x" + y + "=" + (x * y) + "\t");
      }
      System.out.println();
      z++;
    }
  }
}