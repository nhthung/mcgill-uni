/* Author: Le Nhat Hung
 * ID: 260793376
 */

import java.io.*;
import java.util.*;

public class mancala {

    private static final int
        EXIT_FILE_NOT_FOUND = 1,
        EXIT_FILE_CREATION_FAILED = 2,

        MOVE_LEFT = 0,
        MOVE_RIGHT = 1,

        EMPTY = 0,
        PEBBLE = 1,

        START_INDEX = 0,

        OPT_PASS_ZERO = 0,
        OPT_PASS_ONE = 1,
        OPT_PASS_TWO = 2;

    private static final String
        FILE_INPUT = "testMancala.txt",
        FILE_OUTPUT = "testMancala_solutions.txt";

    private int n;
    private int[] solutions;
    private int[][] boards;


    mancala (String file) throws RuntimeException {

        try {
            Scanner f = new Scanner(new File(file));
            n = Integer.parseInt(f.nextLine());
            solutions = new int[n];
            boards = new int[n][];

            for (int i = 0; i < n; i++)

                boards[i] = Arrays.stream(f.nextLine().split("\\s+"))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(EXIT_FILE_NOT_FOUND);
        }
    }

    /* Move subclass, containing index of move and direction */
    private static class Move {

        private int index, direction;

        Move (int index, int direction) {
            this.index = index;
            this.direction = direction;
        }
    }

    /* Macro System.out.println */
    private static void print (Object s) { System.out.println(s); }

    private static void print (int[] board) { print(Arrays.toString(board)); }

    /* Print mancala.boards.
     * For testing, not used in final solution
     */
    private static void print (int[][] boards) {

        for (int i = 0; i < boards.length; i++) {
            print("Problem " + i + ":");
            print(boards[i]);
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

    private static boolean hasPebble (int slot) { return slot == PEBBLE; }

    /* Count number of slots containing pebbles on the board */
    private static int getNumPebbles (int[] board) {

        int numPebbles = 0;

        for (int i = 0; i < board.length; i++)
            if ( hasPebble(board[i]) )
                numPebbles++;

        return numPebbles;
    }

    /* Check if move is possible */
    private static boolean hasMove (int[] board) {

        for (int i = 0; i + 2 < board.length; i++) {

            if ((hasPebble(board[i]) &&
                 hasPebble(board[i + 1]) &&
                 !hasPebble(board[i + 2])) ||

                (!hasPebble(board[i]) &&
                 hasPebble(board[i + 1]) &&
                 hasPebble(board[i + 2])))

                 return true;
        }
        return false;
    }

    /* Check for and return a move, or null if not found */
    private static Move getMove (int[] board, int fromIndex) {

        for (int i = fromIndex; i + 2 < board.length; i++) {

            if ( hasPebble(board[i]) &&
                 hasPebble(board[i + 1]) &&
                !hasPebble(board[i + 2]))

                return new Move(i, MOVE_RIGHT);

            else if (
                !hasPebble(board[i]) &&
                 hasPebble(board[i + 1]) &&
                 hasPebble(board[i + 2]))

                return new Move(i, MOVE_LEFT);
        }
        return null;
    }

    /* Make the move on the board, return the board */
    private static int[] makeMove (int[] board, Move move) {

        if (move.direction == MOVE_LEFT) {
            board[move.index] = PEBBLE;
            board[move.index + 1] = board[move.index + 2] = EMPTY;
        }
        else { /* move.direction == MOVE_RIGHT */
            board[move.index + 2] = PEBBLE;
            board[move.index] = board[move.index + 1] = EMPTY;
        }
        return board;
    }

    /* Solution implemented with optimal substructures */
    private static void opt (mancala Mancala, int[] board, int problem, int fromIndex, int pass) {

        Move move = getMove(board, fromIndex);

        /* Get the lowest number of pebbles as solution */
        if (getNumPebbles(board) < Mancala.solutions[problem])
            Mancala.solutions[problem] = getNumPebbles(board);

        if (move == null) {
            if (fromIndex == START_INDEX)
                return;

            else if (fromIndex + 2 >= board.length)
                opt(Mancala, board, problem, START_INDEX, OPT_PASS_ONE);
        }

        else if (pass == OPT_PASS_ONE)
            opt(Mancala, board, problem, START_INDEX, OPT_PASS_TWO);

        else if (pass == OPT_PASS_TWO)
            return;

        else {
            opt(Mancala, makeMove(board.clone(), move), problem, fromIndex, OPT_PASS_ZERO);
            opt(Mancala, board, problem, move.index + 1, OPT_PASS_ZERO);
        }
    }

    /* Call optimal substructures implementation */
    private static void solve (mancala Mancala, int[] board, int i) {

        opt(Mancala, board, i, START_INDEX, OPT_PASS_ZERO);
    }

    /* Iterate through and solve each problem */
    private static void solve (mancala Mancala) {

        for (
            int i = 0;
            i < Mancala.n;
            Mancala.solutions[i] = getNumPebbles(Mancala.boards[i]),
            solve(Mancala, Mancala.boards[i], i),
            i++
        );
    }

    /* Instantiate mancala object, fill solutions array, write to file */
    public static void main(String[] args) {

        mancala Mancala = new mancala(FILE_INPUT);

        solve(Mancala);
        writeToFile(Mancala.solutions);
    }
}
