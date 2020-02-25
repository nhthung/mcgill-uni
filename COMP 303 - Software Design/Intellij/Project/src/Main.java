// File name: Main.java

import java.util.Scanner;

/**
 * Builds a 9x9 world with 2 Autonomouses, 3 Moveables and 5 Immovables, handplaced.
 *
 * @author Le Nhat Hung
 * @vesion 1.0
 * @since April 12 2018
 */

public class Main {
    static World world;
    static Scanner scanner = new Scanner(System.in);

    /**
     * Builds the desired world.
     */
    private static void buildWorld() {
        world = new World(9, 9);

        Autonomous a1, a2;
        Moveable m1, m2, m3;
        Immovable i1, i2, i3, i4, i5;

        a1 = new Autonomous("Geralt", 'G');
        a2 = new Autonomous("Link", 'L');

        m1 = new Moveable("poo1", 'O');
        m2 = new Moveable("poo2", 'O');
        m3 = new Moveable("poo3", 'O');

        i1 = new Immovable("prick1", 'X');
        i2 = new Immovable("prick2", 'X');
        i3 = new Immovable("prick3", 'X');
        i4 = new Immovable("prick4", 'X');
        i5 = new Immovable("prick5", 'X');

        try {
            world.add(a1, 4, 4);
            world.add(a2, 6, 2);

            world.add(m1, 3, 4);
            world.add(m2, 4, 5);
            world.add(m3, 4, 3);

            world.add(i1, 5, 7);
            world.add(i2, 2, 6);
            world.add(i3, 2, 3);
            world.add(i4, 4, 2);
            world.add(i5, 7, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Infinite loop of simulations until the user says otherwise.
     * Each simulation is 100 steps.
     *
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            buildWorld();

            world.display();

            for (int i = 0; i < 100; i++)
                world.step();

            System.out.print("Would you like to run the simulation again? (yes/no): ");

            if(!scanner.nextLine().equalsIgnoreCase("yes"))
                world.closeWindow();

            world.disableCloseOperation();
            world.closeWindow();
        }
    }
}
