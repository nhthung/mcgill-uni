public class MutableInteger{
  private Integer mutInteger;
  
  public MutableInteger(int value){
    this.mutInteger = new Integer(value);
  }
  
  public void getMutInteger(){
    int integer = mutInteger;
    System.out.println(integer);
  }
  
  public void setMutInteger(int newValue){
    mutInteger = new Integer(newValue);
  }
}
    