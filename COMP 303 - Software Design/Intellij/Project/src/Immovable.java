// File name: Immovable.java

/**
 * The Immovable object to my unstoppable force.
 * Extends Item.
 * Overrides any movement related parent method to blank.
 * Just stays in one place.
 *
 * @author Le Nhat Hung
 * @vesion 1.0
 * @since April 12 2018
 */

public class Immovable extends Item {

    /**
     * Constructor. Throws exception if name is null, blank or if the token is an unprintable character.
     *
     * @param name
     * @param token
     */
    Immovable(String name, char token) {
        if ( name == null || name.isEmpty() || (int)token < 33 || (int)token > 126 )
            throw new IllegalArgumentException("Token must be a printable char and x and y must be at least 0. Must specify name.");

        setName(name);
        setToken(token);
    }

    @Override
    public void setWorld(World world, int row, int col) {}

    @Override
    public void step() {}

    @Override
    public void step(int nextStep) {}

    @Override
    public int getDirection(int possibleDirs) { return 0; }

    @Override
    public void setNextStep(int nextStep) {}
}
