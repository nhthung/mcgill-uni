public class CountString{
  public static int countString(String[] arr, String arg){
    int nullIndex = 0;
    try{
      int counter = 0;
      for(int i = 0; i < arr.length; i++){
        nullIndex = i;
        if(arr[i].equals(arg))
          counter++;
      }
      return counter;
    }
    catch(NullPointerException e){
      System.err.println("null at index of " + nullIndex);
      return -1;
    }
  }
  
  public static void main(String[] args){
    String[] arr = {"keys", "poptart", "marmalade", "keys", null};
    System.out.println(countString(arr, "keys"));
  }
}