public class OfficeChair{
  public final int MIN_HEIGHT = 50;
  public final int MAX_HEIGHT = 100;
  
  private int height;
  
  public OfficeChair(int height){
    if(height < MIN_HEIGHT)
      this.height = MIN_HEIGHT;
    else if(height > MAX_HEIGHT)
      this.height = MAX_HEIGHT;
    else
      this.height = height;
  }
  
  public void setHeight(int height){
    if(height < MIN_HEIGHT)
      this.height = MIN_HEIGHT;
    else if(height > MAX_HEIGHT)
      this.height = MAX_HEIGHT;
    else
      this.height = height;
  }
  
  public String toString(){
    return height + "";
  }
  
  public static void main(String[] args){
    OfficeChair a = new OfficeChair(-2);
    OfficeChair b = new OfficeChair(73);
    System.out.println(a);
    System.out.println(b);
    System.out.println(a);
    a.setHeight(65);
    System.out.println(a);
  }
}