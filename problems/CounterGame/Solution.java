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
            //System.out.println(next+" "+Math.log(next)/Math.log(2));
            next = nextNumber(next);
            winner = winner.equals("Richard")?"Louise":"Richard";
        }


        /*Double sq = Math.pow(n,0.5);
        Integer intVal = sq.intValue();
        while(sq>1) {
            System.out.println(n+" "+sq+" "+intVal+" "+(sq>intVal));
            //Not extact square
            if(sq>intVal) {
                n = n - Double.valueOf(Math.pow(intVal,2)).longValue();
            }    
            else {//Exact square
                n = n/2;    
            }
            sq = Math.sqrt(n);
            intVal = sq.intValue();
            winner = winner.equals("Richard")?"Louise":"Richard";
        }*/
        return winner;
    }

    private static long nextNumber(long n) {
        long powerOfTwo = 2L;
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
        return powerOfTwo;
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
