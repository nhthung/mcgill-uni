class CatRedRoom{
  public static void main(String[] args){
    CatRescue shelter = new CatRescue(5);
    Cat studmuffin = new Cat("Studmuffin", 1.0);
    Cat geralt = new Cat("Geralt", 0.7);
    studmuffin.meow();
    studmuffin.birthday();
    studmuffin.meow();
    shelter.addCat(geralt);
    shelter.addCat(studmuffin);
    
    shelter.print();
    shelter.adopt();
    
    CatRescue2 shelter2 = new CatRescue2(3);
    shelter2.addCat(studmuffin);
    shelter2.addCat(geralt);
    shelter2.print();
    shelter2.adopt();
    shelter2.print();
  }
}