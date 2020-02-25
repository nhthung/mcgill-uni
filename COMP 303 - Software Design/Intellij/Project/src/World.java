// File name: World.java

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * World object. Mainly holds a 2D array for the map, like a chessboard.
 * Aggregates Item objects (Autonomous, Moveable, Immovable).
 *
 * @author Le Nhat Hung
 * @vesion 1.0
 * @since April 12 2018
 */

public class World {
    private Item[][] world;
    private ArrayList<Item> autonomousItems;
    private int maxRow, maxCol, itemCount;

    private JFrame frame;
    private JPanel panel;

    /**
     * Constructor. Makes a map with the desired size.
     * Throws exception if any dimension is smaller than 1.
     * Instantiates JFrame and JPanel to display the world.
     * Sets the JFrame visible.
     *
     * @param rows
     * @param columns
     */
    World(int rows, int columns) {
        if (rows < 1 || columns < 1)
            throw new IllegalArgumentException("Width and height must be at least 1.");

        maxRow = rows - 1;
        maxCol = columns - 1;
        this.itemCount = 0;
        world = new Item[rows][columns];
        autonomousItems = new ArrayList<>();

        // Instantiating JFrame.
        frame = new JFrame("Bigger treats for gooder dogs!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension((800/rows)*columns, 800));

        // Instantiating JPanel.
        panel = new JPanel(new GridLayout(rows, columns));
        panel.setBackground(new Color(217,240,255));

        // Adding panel to frame.
        frame.add(panel);
    }

    /**
     * Iterates through array list of Autonomous objects.
     * For each Autonomous object, send an int representing all possible directions from the object's current position.
     * Receives back from Autonomous 1 chosen random direction.
     * Calls another method, bump(), which moves Autonomous and Moveable items in the chosen direction if possible.
     * If not possible, nextStep = 0 and is sent to the Autonomous object Item.setNextStep(nextStep).
     * Finally, World.step() calls Autonomous.step().
     */
    public void step() {
        int nextStep;
        int row, col;

        for (Item item: autonomousItems) {
            row = item.getX();
            col = item.getY();

            nextStep = item.getDirection( getPossibleDirs(Item.DIRECTION_MASK, row, col) );

            nextStep = bump(nextStep, row, col);

            if (nextStep == Item.NO_DIRECTION)
                item.setNextStep(nextStep);

            item.step();
        }

        refresh();
    }

    /**
     * Make map visible.
     */
    public void display() {
        // Making frame visible.
        frame.setVisible(true);

        // Putting every grid on map for the first time.
        refresh();
    }

    /**
     * Add Item object to map.
     * Throws many exceptions for any meanies out there.
     *
     * @param item
     * @param row
     * @param col
     * @throws Exception
     */
    public void add(Item item, int row, int col) throws Exception {
        if (itemCount == (maxRow + 1)*(maxCol + 1))
            throw new Exception("No space left.");

        else if ( row < 0 || row > maxRow || col < 0 || col > maxCol )
            throw new IllegalArgumentException("Position provided is out of this world.");

        else if ( world[row][col] != null )
            throw new IllegalAccessException("Position already occupied!");

        world[row][col] = item;

        if (item instanceof Autonomous)
            autonomousItems.add(item);

        itemCount++;

        item.setWorld(this, row, col);
    }

    /**
     * Used in Autonomous and Moveable at the end of their step methods to update the world's 2D map array.
     * Throws exception if position provide is out of bounds.
     *
     * @param nextStep
     * @param row
     * @param col
     */
    public void notify(int nextStep, int row, int col) {
        if ( row < 0 || row > maxRow || col < 0 || col > maxCol )
            throw new IllegalArgumentException("Position provided is out of this world.");

        else if ( world[row][col] == null )
            throw new IllegalArgumentException("No item at this position.");

        // If the object isn't an Immovable, then update the map array.
        else if ( !(world[row][col] instanceof Immovable) ) {
            switch (nextStep) {
                case Item.DIRECTION_RIGHT:
                    world[row][col+1] = world[row][col];
                    world[row][col] = null;
                    break;

                case Item.DIRECTION_UP:
                    world[row-1][col] = world[row][col];
                    world[row][col] = null;
                    break;

                case Item.DIRECTION_LEFT:
                    world[row][col-1] = world[row][col];
                    world[row][col] = null;
                    break;

                case Item.DIRECTION_DOWN:
                    world[row+1][col] = world[row][col];
                    world[row][col] = null;
                    break;
            }
        }
    }

    /**
     * Used in main method.
     * Once the user chooses to rerun the simulator. Main closes the previous simulation's world window.
     * Main has to disable the closing operation first before closing the window,
     * or else closing the window would end the program.
     */
    public void disableCloseOperation() {
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Used in main method to close the window after a simulation's done.
     */
    public void closeWindow() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Refreshes the map window (JFrame and JPanel).
     * Is at end of each World.step().
     */
    private void refresh() {

        // First, removing everything from the JPanel.
        panel.removeAll();

        // Putting every grid back on with the update Item positions.
        for ( int row = 0; row <= maxRow; row++ ) {
            for ( int col = 0; col <= maxCol; col++ ) {
                Item item = world[row][col];

                JLabel label = new JLabel();
                Color color = new Color(255, 255, 255);

                label.setBorder(BorderFactory.createLineBorder(color));
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(new Font("Helvetica", Font.PLAIN, 28));

                // Setting colors for each Item.
                if (item != null) {
                    label.setText(String.valueOf(item.getToken()));

                    if (item instanceof Autonomous)
                        color = new Color(111,115,210);

                    else if (item instanceof Moveable)
                        color = new Color(163,213,255);

                    else if (item instanceof Immovable)
                        color = new Color(118,129,179);

                    label.setForeground(color);
                }

                panel.add(label);
            }
        }

        frame.pack();

        // NECESSARY nap for refresh to finish because step() is too fast.
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Takes in an int representing directions, looks in all 4 directions to see if going in any of them is possible.
     *
     * @param directions
     * @param row
     * @param col
     * @return possibleDirs An int representing the possible directions an Item can go at its current position.
     */
    private int getPossibleDirs(int directions, final int row, final int col) {
        int possibleDirs = directions;

        if (col + 1 > maxCol || world[row][col+1] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_RIGHT;

        if (row + 1 > maxRow || world[row+1][col] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_DOWN;

        if (col - 1 < 0 || world[row][col-1] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_LEFT;

        if (row - 1 < 0 || world[row-1][col] instanceof Immovable)
            possibleDirs &= ~Item.DIRECTION_UP;

        return possibleDirs;
    }

    /**
     * Recursive method. Surprised it works.
     * World.step() calls this method in each of its loop iteration.
     * Moves Moveable and Autonomous objects when bumped, if possible.
     *
     * @param nextStep
     * @param row
     * @param col
     * @return
     */
    private int bump(int nextStep, final int row, final int col) {
        Item nextItem;
        int nextRow = row;
        int nextCol = col;

        switch (nextStep) {
            case Item.DIRECTION_UP: nextRow--; break;
            case Item.DIRECTION_LEFT: nextCol--; break;
            case Item.DIRECTION_DOWN: nextRow++; break;
            case Item.DIRECTION_RIGHT: nextCol++; break;
        }

        if (nextRow < 0 || nextRow > maxRow || nextCol < 0 || nextCol > maxCol)
            return Item.NO_DIRECTION;

        else
            nextItem = world[nextRow][nextCol];

        if (nextItem == null)
            return nextStep;

        // If the bumped object is Autonomous or Moveable, calls bump on the bumped object itself to see if it bumps any other object.
        else if (nextItem instanceof Autonomous || nextItem instanceof Moveable){
            nextStep = getPossibleDirs(nextStep, nextRow, nextCol);

            if (nextStep == Item.NO_DIRECTION)
                return nextStep;

            else {
                nextStep = bump(nextStep, nextRow, nextCol);

                nextItem.step(nextStep);

                return nextStep;
            }
        }

        else
            return Item.NO_DIRECTION;
    }
}
