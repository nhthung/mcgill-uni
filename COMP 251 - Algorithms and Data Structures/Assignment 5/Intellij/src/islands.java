/* Author: Le Nhat Hung
 * ID: 260793376
 */

import java.io.*;
import java.util.*;

public class islands {

    private static final int
        EXIT_FILE_NOT_FOUND = 1,
        EXIT_FILE_CREATION_FAILED = 2,

        NUM_DIRECTIONS = 4,

        WATER = 0,
        LAND = 1,

        ROW = 0,
        COL = 1;

    /* Directions: up, right, down, left */
    private static final int[][]
        DIRECTIONS = { {-1, 0, 1,  0},
                       { 0, 1, 0, -1} };

    private static final String
        FILE_INPUT = "testIslands.txt",
        FILE_OUTPUT = "testIslands_solutions.txt",

        SYMBOL_WATER = "#",
        SYMBOL_LAND = "-";

    private int n; /* Number of problems */
    private int[][][] oceans; /* oceans[i] is double array representing problem number i */


    islands (String file) throws RuntimeException {

        try {
            Scanner f = new Scanner(new File(file));
            n = Integer.parseInt(f.nextLine());
            oceans = new int[n][][];

            for (int i = 0; i < n; i++) {

                int numRows = Integer.parseInt(f.nextLine().split("\\s+")[0]);

                oceans[i] = new int[numRows][];

                for (int j = 0; j < numRows; j++) {

                    oceans[i][j] = Arrays.stream(
                        f.nextLine()
                        .replaceAll(SYMBOL_WATER, Integer.toString(WATER))
                        .replaceAll(SYMBOL_LAND, Integer.toString(LAND))
                        .split(""))
                            .mapToInt(Integer::parseInt)
                            .toArray();
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(EXIT_FILE_NOT_FOUND);
        }
    }

    /* Macro for System.out.println */
    private static void print (Object s) { System.out.println(s); }

    /* Print islands.oceans.
     * For testing, not used in final solution
     */
    private static void print (int[][][] oceans) {

        for (int i = 0; i < oceans.length; i++) {

            print("Problem " + i + ":");

            for (int j = 0; j < oceans[i].length; j++) {

                print(Arrays.toString(oceans[i][j]));
            }
        }
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

    /* Set all cells in discovered[][] to false */
    private static void setFalse (boolean[][] discovered) {

        for (int i = 0; i < discovered.length; i++)
            for (int j = 0; j < discovered[0].length; j++)

                discovered[i][j] = false;
    }

    /* Check if pixel == LAND */
    private static boolean isLand (int pixel) { return pixel == LAND; }

    /* Check if pixel can be included in dfs */
    private static boolean isSafe (int[][] ocean, int row, int col, boolean[][] discovered) {

        /* True if pixel not out of bounds, is land, and undiscovered */
        return
            row >= 0 && row < ocean.length &&
            col >= 0 && col < ocean[0].length &&
            ( isLand(ocean[row][col]) && !discovered[row][col] );
    }

    /* Depth first search neighbors of pixel */
    private static void dfs (int[][] ocean, int row, int col, boolean[][] discovered) {

        /* Mark pixel as discovered */
        discovered[row][col] = true;

        for (int i = 0; i < NUM_DIRECTIONS; i++)

            if (isSafe(
                ocean,
                row + DIRECTIONS[ROW][i],
                col + DIRECTIONS[COL][i],
                discovered))

                dfs(ocean,
                    row + DIRECTIONS[ROW][i],
                    col + DIRECTIONS[COL][i], discovered);
    }

    /* Call dfs on all undiscovered land pixels of ocean.
     * Count and return number of islands in ocean
     */
    private static int solve (int[][] ocean) {

        int
            height = ocean.length,
            width = ocean[0].length,
            islandCount = 0;

        boolean[][] discovered = new boolean[height][width];
        setFalse(discovered);

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                if ( isLand(ocean[i][j]) && !discovered[i][j] ) {

                    dfs(ocean, i, j, discovered);
                    islandCount++;
                }
        return islandCount;
    }

    /* Iterate through all problems and count islands for each */
    private static int[] solve (islands Islands) {

        int[] solutions = new int[Islands.n];

        for (
            int i = 0;
            i < Islands.n;
            solutions[i] = solve(Islands.oceans[i]), i++
        );

        return solutions;
    }

    /* Instantiate islands object, get solution array, write array to file */
    public static void main (String[] args) {

        islands Islands = new islands(FILE_INPUT);

        int[] solutions = solve(Islands);
        writeToFile(solutions);
    }
}
