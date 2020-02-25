import java.util.*;
import java.io.*;

public class Multiply{

    private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return rand.nextInt(maxval + 1);
    }
    
    public static int[] naive(int size, int x, int y) {

        // YOUR CODE GOES HERE  (Note: Change return statement)

        int[] result = new int[2];

        if (size == 1) {
            result[0] = x * y;
            result[1] = 1;
            return result;
        }
        else {
            double m = Math.ceil((double)size / 2);
            int a = x >> (int)m, b = x - (a << (int)m);
            int c = y >> (int)m, d = y - (c << (int)m);

            int[] e = naive((int)m, a, c);
            int[] f = naive((int)m, b, d);
            int[] g = naive((int)m, b, c);
            int[] h = naive((int)m, a, d);

            /* 2^(2m) e + 2^m (g + h) + f */
            result[0] = (e[0] << (2 * (int)m)) + ((g[0] + h[0]) << (int)m) + f[0];
            result[1] = 3 * (int)m + e[1] + f[1] + g[1] + h[1];

            return result;
        }
    }

    public static int[] karatsuba(int size, int x, int y) {
        
        // YOUR CODE GOES HERE  (Note: Change return statement)

        int[] result = new int[2];

        if (size == 1) {
            result[0] = x * y;
            result[1] = 1;
            return result;
        }
        else {
            double m = Math.ceil((double)size / 2);
            int a = x >> (int)m, b = x - (a << (int)m);
            int c = y >> (int)m, d = y - (c << (int)m);

            int[] e = karatsuba((int)m, a, c);
            int[] f = karatsuba((int)m, b, d);
            int[] g = karatsuba((int)m, a - b, c - d);

            /* 2^(2m) e + 2^m (g + h) + f */
            result[0] = (e[0] << (2 * (int)m)) + ((e[0] + f[0] - g[0]) << (int)m) + f[0];
            result[1] = 6 * (int)m + e[1] + f[1] + g[1];

            return result;
        }
    }
    
    public static void main(String[] args){

        try{
            int maxRound = 20;
            int maxIntBitSize = 16;
            for (int size=1; size<=maxIntBitSize; size++) {
                int sumOpNaive = 0;
                int sumOpKaratsuba = 0;
                for (int round=0; round<maxRound; round++) {
                    int x = randomInt(size);
                    int y = randomInt(size);
                    int[] resNaive = naive(size,x,y);
                    int[] resKaratsuba = karatsuba(size,x,y);

                    if (resNaive[0] != resKaratsuba[0]) {
                        throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
                    }
                    
                    if (resNaive[0] != (x*y)) {
                        int myproduct = x*y;
                        throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
                    }
                    
                    sumOpNaive += resNaive[1];
                    sumOpKaratsuba += resKaratsuba[1];
                }
                int avgOpNaive = sumOpNaive / maxRound;
                int avgOpKaratsuba = sumOpKaratsuba / maxRound;
                System.out.println(size + "," + avgOpNaive + "," + avgOpKaratsuba);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
   } 
}
