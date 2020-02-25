public class EditDistace{
  public static int dist(String a, String b){
    if(a.equals(b))
      return 0;
    else if(a.equals(""))
      return b.length();
    else if(b.equals(""))
      return a.length();
    if(a.charAt(0) == b.charAt(0))
      return dist(a.substring(1), b.substring(1));
    else
      return 1 + Math.min(Math.min(dist(a, b.substring(1)), dist(a.substring(1), b)),
                          dist(a.substring(1), b.substring(1)));
  }
  
  public static void main(String[] args){
    System.out.println(
                       dist("charadeyouare", "hahacharadeyouare"));
  }
}