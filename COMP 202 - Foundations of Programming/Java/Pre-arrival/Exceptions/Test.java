public class Test {
  public static void main(String[] args) {
    try { foo(); }
    catch (ArrayIndexOutOfBoundsException e) {
      System.err.println("Caught II");
    }
  }
  public static void foo() {
    try {
      int[] a = {1, 2, 3};
      a[4] = 5;
    }
    catch (NullPointerException e) {
      System.out.println("Caught I");
    }
  }
} 