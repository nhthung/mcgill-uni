public class FahrenheitToCelsius{
  public static void main(String[] args){
    String stringtemp = args[0];
    double tempc = (Double.parseDouble(stringtemp) - 32) * 5 / 9;
    System.out.println("The temperature is " + tempc + "°C.");
  }
}
