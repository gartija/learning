import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'sumXor' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts LONG_INTEGER n as parameter.
     */

    public static long sumXor(long n) {
    // Write your code here
        if(n==0L) return 1L;//Special case?
        double log2 = Math.log(n)/Math.log(2);
        int log2Int = Double.valueOf(log2).intValue();
        //I count the number of 1s on the binary representation of the number. The I substract the amount of 1s from the closes log2 and calculate power of 2 with that result
        return Double.valueOf(Math.pow(2,log2Int-(Long.bitCount(n)-1))).longValue();
        
        /* Brute force solution
        long ret = 0L;
        for(long i=0L;i<=n;i++) {
            if((n+i)==(n^i)) {
                ret++;
            } 
        }*/
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        long n = Long.parseLong(bufferedReader.readLine().trim());

        long result = Result.sumXor(n);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
