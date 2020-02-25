// File name: Moveable.java

/**
 * Moveable object. Extends Item.
 * Moves when bumped. Its step method is directly inherited from Item.
 *
 * @author Le Nhat Hung
 * @vesion 1.0
 * @since April 12 2018
 */

public class Moveable extends Item {

    /**
     * Constructor. Throws exception if name is null, blank or if the token is an unprintable character.
     */
    Moveable(String name, char token) {
        if ( name == null || name.isEmpty() || (int)token < 33 || (int)token > 126 )
            throw new IllegalArgumentException("Token must be a printable char and x and y must be at least 0. Must specify name");

        setName(name);
        setToken(token);
    }

    @Override
    public void step() {}

    @Override
    public int getDirection(int possibleDirs) { return 0; }

    @Override
    public void setNextStep(int nextStep) {}
}
