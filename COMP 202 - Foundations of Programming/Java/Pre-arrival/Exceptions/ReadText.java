import java.util.*;
import java.io.*;

public class ReadText{
  public static void main(String[] args){
    try {
      Scanner scan = new Scanner(new File("test.txt"));
      int counter = 1;
      while(scan.hasNextLine())
        System.out.println(counter++ + "\t" + scan.nextLine());
    }
    catch (FileNotFoundException fnfe) {
      System.err.println("test.txt cannot be found.");
    }
  }
}