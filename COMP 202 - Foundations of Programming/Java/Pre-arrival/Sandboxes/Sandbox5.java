public class Sandbox5{
  public static void main(String[] args){
    int[] arr = {1, 2, 3, 4, 5};
    for(int i = 0; i < arr.length; i++)
      System.out.print(arr[i] + " ");
    System.out.println();
    deleteElement(arr, 2);
    for(int i = 0; i < arr.length; i++)
      System.out.print(arr[i] + " ");
  }
  
  public static void deleteElement(int[] arr, int i){
    for (int j = i; j < arr.length - 1; j++)
      arr[j] = arr[j + 1];
    arr[arr.length - 1] = 0;
  }
}