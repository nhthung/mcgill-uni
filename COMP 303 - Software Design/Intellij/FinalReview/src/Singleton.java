import java.util.Random;

public class Singleton {
    private Random random;

    private Singleton() {
        random = new Random();
    }

    private static Singleton reference = new Singleton();

    public static Singleton getReference() {
        return reference;
    }

    public void setSeed(int seed) {
        random.setSeed(seed);
    }

    public int nextInt() {
        return random.nextInt();
    }
}
