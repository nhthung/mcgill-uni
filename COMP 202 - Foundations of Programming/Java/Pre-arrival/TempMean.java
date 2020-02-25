import java.util.Scanner;
public class TempMean{
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    System.out.print("How many days? ");
    int ndays = scan.nextInt();
    double[] temps = new double[ndays];
    double sumTemps = 0;
    System.out.println("The temparatures are?");
    for(int i = 0; i < ndays; i++){
      temps[i] = scan.nextDouble();
      sumTemps += temps[i];
    }
    System.out.printf("Mean temperature is %.1f", sumTemps/ndays);
  }
}