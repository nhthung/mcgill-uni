public class SimpleCalc{
  public static void main(String[] args){
    String operand1 = args[0];
    String operator = args[1];
    String operand2 = args[2];
    if(operator.equals("+")){
      System.out.println(Integer.parseInt(operand1) + Integer.parseInt(operand2));
    }
    if(operator.equals("-")){
      System.out.println(Integer.parseInt(operand1) - Integer.parseInt(operand2));
    }
    if(operator.equals("*")){
      System.out.println(Integer.parseInt(operand1) * Integer.parseInt(operand2));
    }
    if(operator.equals("/")){
      System.out.println(Integer.parseInt(operand1) / Integer.parseInt(operand2));
    }
    if(operator.equals("%")){
      System.out.println(Integer.parseInt(operand1)%Integer.parseInt(operand2));
    }
  }
}