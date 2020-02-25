public class Dog{
  private String name;
  
  public Dog(String name){
    this.name = name;
  }
  
  public void setName(String name){
    this.name = name;
  }
  
  public String toString(){
    return name;
  }
  
  public static void main(String[] args){
    Dog god = new Dog("God");
    System.out.println(god);
    god.setName("Gaunter O'Dimm");
    System.out.println(god);
  }
}