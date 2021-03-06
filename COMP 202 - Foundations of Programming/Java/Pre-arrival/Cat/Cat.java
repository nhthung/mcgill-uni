public class Cat{
  private String name;
  private double age;
  
  public Cat(String name){
    this.name = name;
    this.age = 0.0;
  }
  
  public Cat(String name, double age){
    this.name = name;
    this.age = age;
  }
  
  public String toString(){
    return name + ": " + age + " year(s) old";
  }
  
  public double getAge(){
    return age;
  }
  
  public String getName(){
    return name;
  }
  
  public void meow(){
    if (age < 1.0)
      System.out.println(name + " mews \n");
    else
      System.out.println(name + " meows \n");
  }
  
  public void birthday(){
    age++;
  }
  
  public boolean equals(Cat thatCat){
    return name.equals(thatCat.name) && age == thatCat.age;
  }
}