import java.util.Random;

public class Autonomous extends Item {
    private int nextStep;

    private Random random = new Random();

    Autonomous(String name, char token) {
        if ( name == null || name.isEmpty() || (int)token < 33 || (int)token > 126 )
            throw new IllegalArgumentException("Token must be a printable char and x and y must be at least 0. Must specify name");

        setName(name);
        setToken(token);
    }

    @Override
    public void step() {
        switch (nextStep) {
            case DIRECTION_UP:
                setY(getY() + 1);
                break;

            case DIRECTION_LEFT:
                setX(getX() - 1);
                break;

            case DIRECTION_DOWN:
                setY(getY() - 1);
                break;

            case DIRECTION_RIGHT:
                setX(getX() + 1);
                break;
        }
    }

    @Override
    public int getDirection(int possibleDirs) {
        int r = random.nextInt(NumDirections[possibleDirs]);
        nextStep = PickDirection[possibleDirs][r];

        return nextStep;
    }

    @Override
    public void setNextStep(int nextStep) {
        this.nextStep = nextStep;
    }
}
