/* Author: Le Nhat Hung
 * ID: 260793376
 */

import java.io.*;
import java.util.*;

public class balloon {

    private static final int
        EXIT_FILE_NOT_FOUND = 1,
        EXIT_FILE_CREATION_FAILED = 2,
        BALLOON_POPPED = 0;

    private static String
        FILE_INPUT = "testBalloons.txt",
        FILE_OUTPUT = "testBalloons_solution.txt";

    private int n; /* Number of problems */
    private int[][] h; /* Heights of each balloon for each problem */


    balloon (String file) throws RuntimeException {

        try {
            Scanner f = new Scanner(new File(file));
            n = Integer.parseInt(f.nextLine()); /* 1st line is number of problems */
            h = new int[n][];

            f.nextLine(); /* Skip 2nd line */

            for (int i = 0; i < n; i++)

                h[i] = Arrays.stream(f.nextLine().split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(EXIT_FILE_NOT_FOUND);
        }
    }

    /* Macro for System.out.println */
    private static void print (Object s) {
        System.out.println(s);
    }

    /* Print balloon.h.
     * For testing, not used in final solution
     */
    private static void print (int[][] h) {

        for (
            int i = 0;
            i < h.length;
            print("Problem " + i + ": " + Arrays.toString(h[i])), i++
        );
    }

    /* Write solution to all n problems to file */
    private static void writeToFile (int[] solutions) {

        try {
            PrintWriter solutionFile = new PrintWriter(FILE_OUTPUT, "UTF-8");

            for (
                int i = 0;
                i < solutions.length;
                solutionFile.println(solutions[i]), i++
            );

            solutionFile.close();
        }
        catch (IOException e) {
            System.err.println("Solutions file creation failed.");
            System.exit(EXIT_FILE_CREATION_FAILED);
        }
    }

    /* Return true if height of balloon == BALLOON_POPPED */
    private static boolean isPopped (int height) { return height == BALLOON_POPPED; }

    /* Return true if height of all balloons in array == BALLOON_POPPED */
    private static boolean isPopped (int[] heights) {

        for (int i = 0; i < heights.length; i++)

            if ( !isPopped(heights[i]) )
                return false;

        return true;
    }

    /* Choose height at which to shoot arrow.
     * Pick the height of the unpopped balloon closest to the shooter
     */
    private static int getArrowHeight(int[] heights) {

        int i;

        /* Get height of balloon closest to the shooter */
        for (i = 0; i < heights.length; i++)

            if ( !isPopped(heights[i]) ) break;

        return heights[i];
    }

    /* Shoot one arrow, setting balloon's height to BALLOON_POPPED if hit */
    private static void shootArrow (int[] heights) {

        int arrowHeight = getArrowHeight(heights);

        for (int i = 0; i < heights.length; i++) {

            /* If balloon isn't popped and has same height as arrow's,
             * pop it and decrement height of arrow
             */
            if ( !isPopped(heights[i]) && heights[i] == arrowHeight ) {
                heights[i] = BALLOON_POPPED;
                arrowHeight--;
            }

            /* If arrow height == 0, then stop function */
            if (arrowHeight == 0) break;
        }
    }

    /* Solve one problem among the n problems */
    private static int solve (int[] heights) {

        int arrowCount = 0;

        /* While there are still unpopped balloons, shoot an arrow */
        while ( !isPopped(heights) ) {
            shootArrow(heights);
            arrowCount++;
        }

        return arrowCount;
    }

    /* Solve all n problems */
    private static int[] solve (balloon Balloon) {

        int[] solutions = new int[Balloon.n];

        for (
            int i = 0;
            i < Balloon.n;
            solutions[i] = solve(Balloon.h[i]), i++
        );

        return solutions;
    }

    /* Instantiate balloon object, get solutions array, write array to file */
    public static void main (String[] args) {

        balloon Balloon = new balloon(FILE_INPUT);

        int[] solutions = solve(Balloon);
        writeToFile(solutions);
    }
}
