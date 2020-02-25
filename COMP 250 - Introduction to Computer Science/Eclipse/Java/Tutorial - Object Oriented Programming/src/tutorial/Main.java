package tutorial;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		//Employee e1 = new Employee("Bob");
		
		//Manager m1 = new Manager("Bill");
		
		JOptionPane.showMessageDialog(null, "Hello World");
		//JOptionPane.showMessageDialog(null, "you have been spooked", "by the spookster", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("image.jpg"));
		JOptionPane.showMessageDialog(null, "you have been spooked", "by the spookster", JOptionPane.INFORMATION_MESSAGE, new CustomIcon(300));
		
		ArrayList<Country> countries = new ArrayList<Country>();
		countries.add(new Country("Uruguay", 100000));
		countries.add(new Country("Belgium", 100));
		countries.add(new Country("Belgium", 5));
		
		Collections.sort(countries);
		
		for(Country c: countries) {
			System.out.println(c.getName() + " " + c.getArea());
		}
		
		System.out.println("hello world".compareTo("hello"));
	}

}
