public class Main {
    static World aWorld;
    static Autonomous auto;
    static Moveable m1, m2, m3, m4, m5, m6, m7, m8;
    static Immovable i1, i2, i3, i4;

    private static void BuildWorld() {
        aWorld = new World(5, 5);
        auto = new Autonomous("poo", '&');
        m1 = new Moveable("poop1", 's');
        m2 = new Moveable("poop2", 'y');
        m3 = new Moveable("poop3", 'a');
        m4 = new Moveable("poop4", 'o');
        m5 = new Moveable("poop1", 's');
        m6 = new Moveable("poop2", 'y');
        m7 = new Moveable("poop3", 'a');
        m8 = new Moveable("poop4", 'o');
        i1 = new Immovable("i", 'a');
        i2 = new Immovable("i", 'a');
        i3 = new Immovable("i", 'a');
        i4 = new Immovable("i", 'a');

        try {
            aWorld.add(auto, 2, 2);
            aWorld.add(m1, 1, 2);
            aWorld.add(m2, 2, 1);
            aWorld.add(m3, 3, 2);
            aWorld.add(m4, 2, 3);
            aWorld.add(i1, 0, 2);
            aWorld.add(i2, 2, 0);
            aWorld.add(i3, 4, 2);
            aWorld.add(i4, 2, 4);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BuildWorld();

        System.out.println("auto coors: [" + auto.getX() + ", " + auto.getY() + "]");
        System.out.println("m1 coors: [" + m1.getX() + ", " + m1.getY() + "]");
        System.out.println("m2 coors: [" + m2.getX() + ", " + m2.getY() + "]");
        System.out.println("m3 coors: [" + m3.getX() + ", " + m3.getY() + "]");
        System.out.println("m4 coors: [" + m4.getX() + ", " + m4.getY() + "]");
        System.out.println("i1 coors: [" + i1.getX() + ", " + i1.getY() + "]");
        System.out.println("i2 coors: [" + i2.getX() + ", " + i2.getY() + "]");
        System.out.println("i3 coors: [" + i3.getX() + ", " + i3.getY() + "]");
        System.out.println("i4 coors: [" + i4.getX() + ", " + i4.getY() + "]");

        for ( int i = 0; i < 2; i++ ) {
            aWorld.step();
            System.out.println("auto coors: [" + auto.getX() + ", " + auto.getY() + "]");
            System.out.println("m1 coors: [" + m1.getX() + ", " + m1.getY() + "]");
            System.out.println("m2 coors: [" + m2.getX() + ", " + m2.getY() + "]");
            System.out.println("m3 coors: [" + m3.getX() + ", " + m3.getY() + "]");
            System.out.println("m4 coors: [" + m4.getX() + ", " + m4.getY() + "]");
            //System.out.println("m5 coors: [" + m5.getX() + ", " + m5.getY() + "]");
            //System.out.println("m6 coors: [" + m6.getX() + ", " + m6.getY() + "]");
            //System.out.println("m7 coors: [" + m7.getX() + ", " + m7.getY() + "]");
            //System.out.println("m8 coors: [" + m8.getX() + ", " + m8.getY() + "]");
            System.out.println("i1 coors: [" + i1.getX() + ", " + i1.getY() + "]");
            System.out.println("i2 coors: [" + i2.getX() + ", " + i2.getY() + "]");
            System.out.println("i3 coors: [" + i3.getX() + ", " + i3.getY() + "]");
            System.out.println("i4 coors: [" + i4.getX() + ", " + i4.getY() + "]");
        }
    }
}
