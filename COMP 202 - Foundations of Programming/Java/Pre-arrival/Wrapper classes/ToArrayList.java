import java.util.ArrayList;

public class ToArrayList{
  public static ArrayList<Double> toArrayList(double[] arr){
    ArrayList<Double> list = new ArrayList<Double>();
    for(int i = 0; i < arr.length; i++)
      list.add(arr[i]);
    return list;
  }
  
  public static void main(String[] args){
    double[] arr = {1.0, 2.0, 3.0};
    ArrayList<Double> list = toArrayList(arr);
    for(int i = 0; i < list.size(); i++)
      System.out.print(list.get(i) + " ");
  }
}