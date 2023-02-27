package problems.Waiter;

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
     * Complete the 'waiter' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY number
     *  2. INTEGER q
     */

    public static List<Integer> waiter(List<Integer> number, int q) {
    // Write your code here
        //Collections.reverse(number);
        List<Integer> ret = new ArrayList<>();
        List<Integer> primes = primeGenerator(q);
        primes.stream().forEach(prime -> {
            List<Integer> toAdd=number.stream().filter(n -> n%prime==0).collect(Collectors.toList());
            ret.addAll(toAdd);
            number.removeIf(n -> n%prime==0);
            Collections.reverse(number);
        });
        Collections.reverse(number);
        ret.addAll(number);
        return ret; 
    }

    private static List<Integer> primeGenerator(int q) {
        List<Integer> ret = new ArrayList<>(q);
        //ret.add(1);
        for(int i=0,j=2;i<q;j++) {
            boolean isPrime = true;
            for(int k = 2 ; k<j && isPrime; k++) {
                if(j%k == 0) {
                    isPrime = false;
                }    
            }
            if(isPrime) {
                ret.add(j);
                i++;
            }
        }
        return ret;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> number = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = Result.waiter(number, q);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
