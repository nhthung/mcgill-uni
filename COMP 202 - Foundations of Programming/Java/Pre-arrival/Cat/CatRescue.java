public class CatRescue{
  private Cat[] shelter;
  
  public CatRescue(int capacity){
    shelter = new Cat[capacity];
  }
  
  public void print(){
    for (int i = 0; i < shelter.length; i++){
      if (shelter[i] != null)
        System.out.println(shelter[i]);
    }
    System.out.println();
  }
  
  public boolean addCat(Cat cat){
    for (int i = 0; i < shelter.length; i++){
      if (shelter[i] == null){
        shelter[i] = cat;
        return true;
      }
    }
    return false;
  }
  
  public Cat adopt(){
    Cat oldestCat = new Cat("Sage");
    int oldestCatIndex = 0;
    for (int i = 0; i < shelter.length; i++){
      if (shelter[i] != null && shelter[i].getAge() > oldestCat.getAge()){
        oldestCat = shelter[i];
        oldestCatIndex = i;
      }
    }
    shelter[oldestCatIndex] = null;
    System.out.println(oldestCat);
    return oldestCat;
  }
}