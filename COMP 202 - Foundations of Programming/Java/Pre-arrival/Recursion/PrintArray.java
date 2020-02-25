public class PrintArray{
  private static String s = "";
  
  public static String prePrint(int[] arr, int start){
    if(start == arr.length - 1){
      s += arr[start];
      return s;
    }
    else{
      s += arr[start++] + ", ";
      return prePrint(arr, start);
    }
  }
  
  public static void printArray(int[] arr, int start){
    System.out.println(prePrint(arr, start));
  }
  
  public static void main(String[] args){
    int[] arr = {1, 2, 3, 4, 5};
    printArray(arr, 2);
  }
}