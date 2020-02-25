package tutorial;

public class Employee {
	private String name;	// fields should be private
	private double salary;
	
	public Employee(String name) {
		this.name = name;
		salary = 0;
	}

	// getter
	public String getName() {
		return name;
	}
	
	// setter
	public void setName(String name) {
		this.name = name;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}
}
