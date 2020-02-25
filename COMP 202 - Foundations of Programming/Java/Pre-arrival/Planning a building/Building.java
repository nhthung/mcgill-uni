public class Building{
  private String name;
  private int nRooms;
  private int nRoomsAvailable;
  private double area;
  private double areaAvailable;
  private Room[] rooms;
  
  public Building(String name, int nRooms, double area){
    this.name = name;
    this.nRooms = nRooms;
    this.area = area;
    areaAvailable = area;
    nRoomsAvailable = nRooms;
    rooms = new Room[nRooms];
  }
  
  public String getName(){
    return name;
  }
  
  public void setName(String newName){
    name = newName;
  }
  
  public int getNRooms(){
    return nRooms;
  }
  
  public double getArea(){
    return area;
  }
  
  public double getAvailableArea(){
    return areaAvailable;
  }
  
  public void addRoom(Room room){
    if(getAvailableArea() >= room.getArea() && nRoomsAvailable > 0){
      for(int i = 0; i < nRooms; i++){
        if(rooms[i] == null){
          rooms[i] = room;
          System.out.println(room.getName() + " added successfully \n");
          break;
        }
      }
      areaAvailable -= room.getArea();
      nRoomsAvailable--;
      
    }
    else
      System.out.println("Can't add room \n");
  }
  
  public void removeRoom(String name){
    for(int i = 0; i < nRooms; i++){
      if(rooms[i] != null && rooms[i].getName().equals(name)){
        System.out.println(rooms[i].toString() + " removed \n");
        areaAvailable += rooms[i].getArea();
        rooms[i] = null;
        nRoomsAvailable++;
      }
    }
  }
  
  public void printRooms(){
    for(int i = 0; i < nRooms; i++){
      if(rooms[i] != null){
        System.out.println(rooms[i]);
      }
    }
    System.out.println(areaAvailable + " sq ft left \n");
  }
}