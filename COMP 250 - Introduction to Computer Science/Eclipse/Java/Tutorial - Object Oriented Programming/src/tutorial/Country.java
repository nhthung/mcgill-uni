package tutorial;

public class Country implements Comparable<Country>{	// have to implement Comparable if want compareTo, have to put <type>
	private String name;
	private double area;
	
	public Country(String name, double area) {
		this.setName(name);
		this.setArea(area);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	@Override
	public int compareTo(Country other) {
		if (this.area < other.area) return -1;
		if (this.area > other.area) return 1;
		return 0;
	}


}
