public class Test {
    public static int naive(int size, int x, int y) {

        if (size == 1) {
            return x * y;
        }
        else {
            int m = size / 2;
            int a = x >> m, b = x - (a << m);
            int c = y >> m, d = y - (c << m);

            int e = naive(m, a, c);
            int f = naive(m, b, d);
            int g = naive(m, b, c);
            int h = naive(m, a, d);
            return (e << (2*m)) + ((g + h) << m) + f;
        }
    }

    static int cost = 0;
    static int numCalls = 0;
    static int numIncr = 0;

    public static int[] naiveArr(int size, int x, int y) {

        // YOUR CODE GOES HERE  (Note: Change return statement)

        numCalls++;

        System.out.println("size: " + size);
        if (size == 1) {
            cost++;
            numIncr++;
            return new int[]{x * y, 1};
        }
        else {
            int m = size / 2;
            cost += 3*m;
            numIncr++;
            System.out.println("m: " + m);
            int a = x >> m, b = x - (a << m);
            int c = y >> m, d = y - (c << m);

            int[] e = naiveArr(m, a, c);
            int[] f = naiveArr(m, b, d);
            int[] g = naiveArr(m, b, c);
            int[] h = naiveArr(m, a, d);
            System.out.println("e[1]: " + e[1]);
            System.out.println("f[1]: " + f[1]);
            System.out.println("g[1]: " + g[1]);
            System.out.println("h[1]: " + h[1]);
            return new int[] {
                    /* 2^(2m) e + 2^m (g + h) + f */
                    (e[0] << (2 * m)) + ((g[0] + h[0]) << m) + f[0],
                    3 * m + e[1] + f[1] + g[1] + h[1]
            };
        }
    }

    public static int[] karatsuba(int size, int x, int y) {

        // YOUR CODE GOES HERE  (Note: Change return statement)
        if (size == 1)
            return new int[] {x * y, 1};

        else {
            int m = size / 2;
            int a = x >> m, b = x - (a << m);
            int c = y >> m, d = y - (c << m);

            int[] e = karatsuba(m, a, c);
            int[] f = karatsuba(m, b, d);
            int[] g = karatsuba(m, a - b, c - d);
            return new int[] {
                    /* 2^(2m) e + 2^m (g + h) + f */
                    (e[0] << (2 * m)) + ((e[0] + f[0] - g[0]) << m) + f[0],
                    6 * m + e[1] + f[1] + g[1]
            };
        }
    }

    public static void main(String[] args) {
        /*int n = 15;
        System.out.println("n: " + n);
        int a = n >> 2;
        System.out.println("a: " + a);
        int b = n - (a << 2);
        System.out.println("b: " + b);
        int res = (a << 2) + b;
        System.out.println("res: " + res);

        System.out.println("naive(3, -7, -7): " + naive(3, -7, -7));*/

        int[] res2 = naiveArr(4, 15, 15);
        System.out.println("naiveArr(4, 15, 15): {" + res2[0] + ", " + res2[1] + "}");
        System.out.println("cost: " + cost);
        System.out.println("numCalls: " + numCalls);
        System.out.println("numIncr: " + numIncr);

        int[] res3 = karatsuba(8, 255, 255);
        System.out.println("karatsuba(8, 255, 255): {" + res3[0] + ", " + res3[1] + "}");

        System.out.println(3/2);
    }
}
