public class Immovable extends Item {
    Immovable(String name, char token) {
        if ( name == null || name.isEmpty() || (int)token < 33 || (int)token > 126 )
            throw new IllegalArgumentException("Token must be a printable char and x and y must be at least 0. Must specify name.");

        setName(name);
        setToken(token);
    }

    @Override
    public void step() {}

    @Override
    public void step(int nextStep) {}

    @Override
    public int getDirection(int possibleDirs) { return 0; }

    @Override
    public void setNextStep(int nextStep) {

    }
}
