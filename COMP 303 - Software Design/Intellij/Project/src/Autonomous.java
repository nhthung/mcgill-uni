// File name: Autonomous

import java.util.Random;

/**
 * Defines functionality of Autonomous object. Extends Item abstract class.
 *
 * @author Le Nhat Hung
 * @vesion 1.0
 * @since April 12 2018
 */

public class Autonomous extends Item {
    private int nextStep;
    private Random random = new Random();

    /**
     * Constructor. Throws exception if name is null, blank or if the token is an unprintable character.
     *
     * @param name
     * @param token
     */
    Autonomous(String name, char token) {
        if ( name == null || name.isEmpty() || (int)token < 33 || (int)token > 126 )
            throw new IllegalArgumentException("Token must be a printable char and x and y must be at least 0. Must specify name");

        setName(name);
        setToken(token);
    }

    /**
     * Updates the world array then the objects coordinates accordingly.
     */
    @Override
    public void step() {
        getWorld().notify(nextStep, getX(), getY());

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
     * This method is called in World.step() like this:
     * - World.step() initializes a local variable nextStep.
     * - World.step() calls Autonomous.getDirection(int possibleDirections) so that the latter method returns 1 random direction.
     * - This random direction is saved both into World.step()'s local nextStep and Autonomous' global nextStep field.
     *
     * @param possibleDirs
     * @return nextStep
     */
    @Override
    public int getDirection(int possibleDirs) {
        int r = random.nextInt(NumDirections[possibleDirs]);
        nextStep = PickDirection[possibleDirs][r];

        return nextStep;
    }

    /**
     * Serves in a continuation of World.step()'s inner working:
     * - With the chosen random direction saved into World.step()'s local nextStep, a method
     * in World.java checks for Moveable and Autonomous objects in this direction.
     * - If there are Moveable and Autonomous objects in the direction, but they can't all be bumped because of an Immovable object or the map's edge,
     * then World's nextStep takes the value 0 (no possible directions).
     * - World.step() then calls Autonomous.setNextStep(nextStep) (which would be 0 if the bump was impossible).
     */
    @Override
    public void setNextStep(int nextStep) {
        this.nextStep = nextStep;
    }
}
