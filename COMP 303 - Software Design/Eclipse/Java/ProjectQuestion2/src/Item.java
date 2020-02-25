public abstract class Item {
    private String name;
    private char token;
    private int x, y;

    static final int NO_DIRECTION = 0x0;
    static final int DIRECTION_UP = 0x1;
    static final int DIRECTION_LEFT = 0x2;
    static final int DIRECTION_DOWN = 0x4;
    static final int DIRECTION_RIGHT = 0x8;
    static final int DIRECTION_MASK = 0xf;

    static final byte[] NumDirections = {
            /* 0000 */ 0, /* 0001 */ 1, /* 0010 */ 1, /* 0011 */ 2,
            /* 0100 */ 1, /* 0101 */ 2, /* 0110 */ 2, /* 0111 */ 3,
            /* 1000 */ 1, /* 1001 */ 2, /* 1010 */ 2, /* 1011 */ 3,
            /* 1100 */ 2, /* 1101 */ 3, /* 1110 */ 3, /* 1111 */ 4,
    };

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

    public abstract void step();
    public abstract int getDirection(int possibleDirs);
    public abstract void setNextStep(int nextStep);

    public void step(int nextStep) {
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

    void setName(String name) {
        this.name = name;
    }

    void setToken(char token) {
        this.token = token;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    public char getToken() {
        return token;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
