import java.util.*;
import java.io.*;

public class WriteText{
  public static void main(String[] args){
    Cat[] cats = new Cat[2];
    Cat geralt = new Cat("Geralt", 1);
    Cat yen = new Cat("Yennefer", 1000);
    cats[0] = geralt;
    cats[1] = yen;
    writeCats(cats, "output.txt");
  }
  
  public static void write1(){
    try {
      PrintWriter out = new PrintWriter("output.txt");
      out.println("Us, and them");
      out.println("And after all, we're all just ordinary men");
      out.close();
    }
    catch (FileNotFoundException fnfe) {
      System.err.println("output.txt cannot be found.");
    }
  }
  
  public static void read1(){
    try {
      BufferedReader in = new BufferedReader(new FileReader("test.txt"));
      System.out.println(in.readLine());
      System.out.println(in.readLine());
    }
    catch (IOException ioe){
      System.err.println("I/O exception");
    }
  }
  
  public static void write2(){
    try {
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
      out.println("Me, and you");
      out.println("God only knows it's not what we would choose to do");
      out.close();
    }
    catch (IOException ioe){
      System.err.println("I/O exception");
    }
  }
  
  public static void writeCats(Cat[] cats, String filename){
    try{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
      for(Cat c: cats)
        out.println(c);
      out.close();
    }
    catch(IOException ioe){
      System.err.println("I/O exception");
    }
  }
}