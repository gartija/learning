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
