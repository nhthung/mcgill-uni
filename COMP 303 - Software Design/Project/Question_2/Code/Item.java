// File name: Item.java

/**
 * Abstract class extended by Autonomous, Moveable and Immovable.
 * Autonomous uses bitwise operations and array lookups for random movements.
 * 0001 is UP, 0010 is LEFT, 0100 is DOWN and 1000 is RIGHT.
 * This abstract class defines these directions and arrays.
 *
 * @author Le Nhat Hung
 * @vesion 1.0
 * @since April 12 2018
 */

public abstract class Item {
    private String name;
    private char token;
    private int x, y;
    private World world;

    static final int NO_DIRECTION = 0x0;
    static final int DIRECTION_UP = 0x1;
    static final int DIRECTION_LEFT = 0x2;
    static final int DIRECTION_DOWN = 0x4;
    static final int DIRECTION_RIGHT = 0x8;
    static final int DIRECTION_MASK = 0xf;

    // Array defining the amount of possible directions from an int representing the possible directions.
    static final byte[] NumDirections = {
            /* 0000 */ 0, /* 0001 */ 1, /* 0010 */ 1, /* 0011 */ 2,
            /* 0100 */ 1, /* 0101 */ 2, /* 0110 */ 2, /* 0111 */ 3,
            /* 1000 */ 1, /* 1001 */ 2, /* 1010 */ 2, /* 1011 */ 3,
            /* 1100 */ 2, /* 1101 */ 3, /* 1110 */ 3, /* 1111 */ 4,
    };

    // Array to pick a direction between 0 to 4 possible directions.
    static final byte[][] PickDirection = {
            /* 0000 */ { 0, 0, 0, 0 },
            /* 0001 */ { 1, 0, 0, 0 },
            /* 0010 */ { 2, 0, 0, 0 },
            /* 0011 */ { 1, 2, 0, 0 },
            /* 0100 */ { 4, 0, 0, 0 },
            /* 0101 */ { 1, 4, 0, 0 },
            /* 0110 */ { 2, 4, 0, 0 },
            /* 0111 */ { 1, 2, 4, 0 },
            /* 1000 */ { 8, 0, 0, 0 },
            /* 1001 */ { 1, 8, 0, 0 },
            /* 1010 */ { 2, 8, 0, 0 },
            /* 1011 */ { 1, 2, 8, 0 },
            /* 1100 */ { 4, 8, 0, 0 },
            /* 1101 */ { 1, 4, 8, 0 },
            /* 1110 */ { 2, 4, 8, 0 },
            /* 1111 */ { 1, 2, 4, 8 },
    };

    /**
     * Step method to be defined in Autonomous, and overridden by Moveable and Immovable.
     */
    public abstract void step();

    /**
     * Used by Autonomous, overridden by Moveable and Immovable.
     *
     * @param possibleDirs
     * @return int int which in binary represent 1 direction chosen randomly.
     */
    public abstract int getDirection(int possibleDirs);

    /**
     * Used by Autonomous, overridden by Moveable and Immovable.
     * Autonomous has a nextStep field utilized by the step() method to update the object's coordinates.
     * This method is that field's mutator.
     *
     * @param nextStep
     */
    public abstract void setNextStep(int nextStep);

    /**
     * Step method which takes in a nextStep int directly to update the object's coordinates.
     * Used by Autonomous and Moveable when bumped, but overridden by Immoveable.
     *
     * @param nextStep
     */
    public void step(int nextStep) {
        world.notify(nextStep, x, y);

        switch (nextStep) {
            case DIRECTION_UP:
                setX(getX() - 1);
                break;

            case DIRECTION_LEFT:
                setY(getY() - 1);
                break;

            case DIRECTION_DOWN:
                setX(getX() + 1);
                break;

            case DIRECTION_RIGHT:
                setY(getY() + 1);
                break;
        }
    }

    /**
     * Sends a pointer to the world, to the Item. Used to set Item's initial coordinates,
     * and to update the world's ground array from within the Item step methods (except when in Immoveable).
     *
     * @param world
     * @param row
     * @param col
     */
    void setWorld(World world, int row, int col) {
        this.world = world;
        this.x = row;
        this.y = col;
    }

    void setName(String name) { this.name = name; }
    void setToken(char token) { this.token = token; }
    void setX(int row) { this.x = row; }
    void setY(int col) { this.y = col; }

    /**
     * Used to access world's array in Autonomous and Moveable's step methods.
     *
     * @return world The World object in which the Item currently exists.
     */
    World getWorld() { return world; }

    public char getToken() { return token; }
    int getX() { return x; }
    int getY() { return y; }
}
