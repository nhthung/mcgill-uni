public class Player{
  private double[] location = {0, 0, 0};
  private int grenadeStock = 5;
  private double lifeLeft;
  
  public Player(){
    System.out.println("You're alive");
  }
  
  public void grenadeAdd(int X){
    grenadeStock += X;
  }
  
  public void grenadeStatus(){
    System.out.println(grenadeStock);
  }
  
  public void grenadeFragOut(){
    System.out.println("Boom!");
    grenadeStock--;
  }
}