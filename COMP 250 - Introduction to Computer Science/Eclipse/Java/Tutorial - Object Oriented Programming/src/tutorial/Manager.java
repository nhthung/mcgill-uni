package tutorial;

public class Manager extends Employee{
	private double bonus;
	
	public Manager(String name) {
		super(name);	// calls superclass' constructor
		bonus = 0;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}
	
	public double getSalary() {
		return bonus + super.getSalary();	// calls superclass' getSalary();
	}
}
