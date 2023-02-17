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
     * Complete the 'counterGame' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts LONG_INTEGER n as parameter.
     */

    public static String counterGame(long n) {
    // Write your code here
        String winner = "Richard";
        long next = n;
        while(next>1) {
            double logTwo = Math.log(next)/Math.log(2);
            //Case exact POW2 works
            if(logTwo==Double.valueOf(logTwo).intValue()) {
                if(logTwo%2!=0) {
                    winner = winner.equals("Richard")?"Louise":"Richard";
                }
                next = 1;    
            }
            else {
                next = next-Double.valueOf(Math.pow(2,Double.valueOf(logTwo).intValue())).longValue();
                winner = winner.equals("Richard")?"Louise":"Richard";
            }
        }
        return winner;
    }

    private static long nextNumber(long n) {
        
        double logTwo = Math.log(n)/Math.log(2);
        double closestPow = Math.pow(2,Double.valueOf(logTwo).intValue());
        if(logTwo>Double.valueOf(logTwo).intValue()) {
            System.out.println(" Not found pow "+(n-Double.valueOf(closestPow).longValue())+" n, closestPow, log2, potencia "+n+", "+Double.valueOf(closestPow).longValue()+", "+logTwo+" "+Double.valueOf(logTwo).intValue());
            return n-Double.valueOf(closestPow).longValue();
        }
        else {
            System.out.println("Found pow "+n/2);
            return n/2;
        }
        /*long powerOfTwo = 2L;
        for(int i=1;i<=64;i++) {
            if(n==powerOfTwo) {
                //System.out.println("Found pow "+n/2);
                return n/2;
            }
            else if(n<powerOfTwo) {
                //System.out.println(n+" Not found pow "+(n-powerOfTwo/2));
                return n-powerOfTwo/2;
            }
            else {
                powerOfTwo*=2;
            }
        }
        //It shouldn't get to this point
        return powerOfTwo;*/
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                long n = Long.parseLong(bufferedReader.readLine().trim());

                String result = Result.counterGame(n);

                bufferedWriter.write(result);
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
