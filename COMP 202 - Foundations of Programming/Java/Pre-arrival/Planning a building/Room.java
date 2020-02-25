public class Room{
  private String name;
  private double area;
  
  public Room(String name, double area){
    this.name = name;
    this.area = area;
  }
  
  public String getName(){
    return name;
  }
  
  public void setName(String newName){
    name = newName;
  }
  
  public double getArea(){
    return area;
  }
  
  public String toString(){
    return this.name + " " + this.area + " sq ft";
  }
}