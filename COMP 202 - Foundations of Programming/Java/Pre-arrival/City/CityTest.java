public class CityTest{
  private String name;
  private Integer population;
  
  public CityTest(String name, int population){
    this.name = name;
    this.population = population;
  }
  
  public String toString(){
    return name + ": " + population;
  }
  
  public static void main(String[] args){
    CityTest novigrad = new CityTest("Novigrad", 100000);
    System.out.println(novigrad);
    CityTest oxenfurt = new CityTest("Oxenfurt", 12000);
    System.out.println(novigrad);
  }
}