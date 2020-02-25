public class Student{
  private String name;
  private int studentId;
  
  public Student(String name, int studentId){
    this.name = name;
    this.studentId = studentId;
  }
  
  public void printName(){
    System.out.println(name);
  }
  
  public static void main(String[] args){
    Student s1 = new Student("Hacksaw Jim", 123);
    Student s2 = new Student("Brutus Beefcake", 321);
    s1.printName();
    s2.printName();
    System.out.println(s1.name);
    System.out.println(s2.name);
  }
}