public class GreetingProducer implements Runnable {
    String greeting;

    public GreetingProducer(String greeting) {
        this.greeting = greeting;
    }

    public void hello() {
        System.out.println("hei");
    }

    @Override
    public void run() {
        try {
            for ( int i = 1; i <= 10; i++ ) {
                System.out.println(i + ": " + greeting);

                if (i == 5)
                    Thread.currentThread().interrupt();
                Thread.sleep(100);
            }
        } catch (InterruptedException exception) {}

        return;
    }

    public static void main(String[] args) {
        Runnable r1 = new GreetingProducer("did you say beowulf?");
        Runnable r2 = new GreetingProducer("did you say peepee");

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);

        t1.start();
        t2.start();

        while(!t1.isInterrupted() || !t2.isInterrupted()) {
            System.out.println(t1.getState());
            System.out.println(t2.getState());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
        }
        System.out.println("interrupted");
    }
}
