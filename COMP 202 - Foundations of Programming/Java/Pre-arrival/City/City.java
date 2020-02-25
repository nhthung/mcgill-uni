import java.util.*;

public class City{
  private static Scanner scan = new Scanner(System.in);
  private String name;
  private Integer population;
  private static HashMap<String, Integer> cities = new HashMap<String, Integer>();
  
  public City(String name, Integer population){
    this.name = name;
    this.population = new Integer(population);
    cities.put(name, population);
  }
  
  public static void getPopulation(String name){
    System.out.println("The population of " + name + " is " + cities.get(name));
  }
  
  public String toString(){
    return this.name + ": " + this.population;
  }
  
  public static void main(String[] args){
    String query = "";
    City novigrad = new City("Novigrad", 100000);
    System.out.println(novigrad);
    City oxenfurt = new City("Oxenfurt", 12000);
    System.out.println(novigrad);
    do{
      query = scan.nextLine();
      if(cities.containsKey(query))
        getPopulation(query);
      else if(!query.equals("I'm tired of this"))
        System.out.println("No city with this name here");
      else
        System.out.println("Wow geez thanks");
    } while(!query.equals("I'm tired of this"));
  }
}