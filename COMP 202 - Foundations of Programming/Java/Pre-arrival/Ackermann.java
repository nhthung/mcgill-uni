public class Ackermann{
  public static void main(String[] args){
    String m = args[0]; String n = args[1];
    System.out.println(ack(Integer.parseInt(m), Integer.parseInt(n)));
  }
  
  public static int ack(int m, int n){
    if(m == 0){
      return n + 1;
    } else if(n == 0){
      return ack(m - 1, 1);
    } else{
      return ack(m - 1, ack(m, n - 1));
    }
  }
}