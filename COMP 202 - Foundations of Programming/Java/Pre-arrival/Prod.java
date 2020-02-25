public class Prod{
  public static void main(String[] args) {
    System.out.println(prod(1, 4));
  }
  
  public static int prod(int m, int n) {
    if (m == n) {
      return n;
    } else {
      return n * prod(m, n - 1);
    }
  }
}