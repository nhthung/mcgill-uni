public class Sandbox3{
  public static void createArray(int[] arr){
    arr = new int[15];
  }
  
  public static void changeArray(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      arr[i] = 0;
    }
  }
  
  public static void main(String[] args){
    int[] a = {3, 4, 5};
    createArray(a);
    System.out.println(a.length);
    changeArray(a);
    for(int i = 0; i < a.length; i++)
      System.out.print(a[i] + ", ");
  }
}