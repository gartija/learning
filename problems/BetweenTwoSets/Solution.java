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
     * Complete the 'getTotalX' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static int getTotalX(List<Integer> a, List<Integer> b) {
    // Write your code here
        Integer maxA = Collections.max(a);
        Integer minB = Collections.min(b);
        Integer maxAp=maxA;
        Integer minBp=minB;
        
        for(Integer curAi : a) {
            if(maxA != curAi && maxAp%curAi != 0) {
                maxAp=maxA*curAi;
            }
        }
        System.out.println("MaxAp "+maxAp);
        for(Integer curBi : b) {
            int i = 2;
            while(minB != curBi && curBi%minBp != 0) {
                minBp/=i;
                i++;
            }
        }
        System.out.println("minBp "+minBp);
        if(minBp%maxAp!=0) {
            System.out.println("Oops");
        }
        int j =0;
        for(int x = maxAp;x<=minBp;x*=2,j++);
        System.out.println(" Distance "+j);
        return j;
    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> brr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int total = Result.getTotalX(arr, brr);

        bufferedWriter.write(String.valueOf(total));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
