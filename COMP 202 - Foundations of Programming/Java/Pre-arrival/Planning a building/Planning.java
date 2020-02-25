public class Planning{
  public static void main(String[] args){
    Building b1 = new Building("Dunder mifflin", 6, 700);
    Room livingRoom = new Room("Living room", 175);
    Room bedroom = new Room("Bedroom", 200);
    b1.addRoom(livingRoom);
    b1.addRoom(bedroom);
    b1.printRooms();
    b1.removeRoom("Living room");
    b1.printRooms();
    b1.removeRoom("Bedroom");
    b1.printRooms();
  }
}