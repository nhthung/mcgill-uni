import java.lang.reflect.*;

public class Reflection {
    public static void main(String[] args) {
        GreetingProducer prod = new GreetingProducer("poo");

        Class pee = prod.getClass();
        Package pack = pee.getPackage();
        Class[] interfaces = pee.getInterfaces();
        Field[] fields = pee.getDeclaredFields();
        Constructor[] constructors = pee.getDeclaredConstructors();
        Method[] methods = pee.getDeclaredMethods();

        System.out.println(pee.getName());
        System.out.println(pack.getName());

        for (Class i: interfaces)
            System.out.println(i.getName());

        for (Field f: fields) {
            System.out.println(f.getName());
            System.out.println( Modifier.isStatic(f.getModifiers()) );
        }

        for (Constructor c: constructors)
            System.out.println(c.getName());

        Method m = null;

        try {
            m = pee.getDeclaredMethod("hello", null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        System.out.println(m.getName());

        try {
            m.invoke(prod);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        Field f = null;

        try {
            f = pee.getDeclaredField("greeting");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        f.setAccessible(true);

        try {
            System.out.println(f.get(prod));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            f.set(prod, "CACA");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Object arrayVal = Array.get(methods, 0);

        System.out.println(((Method)arrayVal).getName());

        //Array.set(methods, 0, "YE");

        System.out.println(Array.getLength(methods));

        Object newArray = Array.newInstance(methods.getClass().getComponentType(), 2*Array.getLength(methods) + 1);

        System.arraycopy(methods, 0, newArray, 0, Array.getLength(methods));

        methods = (Method[]) newArray;

        for(Method me: methods) {
            if (me == null)
                System.out.println(me.getName());
        }
    }
}
