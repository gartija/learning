package problems.EqualStacks;

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
     * Complete the 'equalStacks' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY h1
     *  2. INTEGER_ARRAY h2
     *  3. INTEGER_ARRAY h3
     */

    public static int equalStacks(List<Integer> h1, List<Integer> h2, List<Integer> h3) {
    // Write your code here
        Integer sumS1 = h1.stream().mapToInt(Integer::intValue).sum();
        Integer sumS2 = h2.stream().mapToInt(Integer::intValue).sum();
        Integer sumS3 = h3.stream().mapToInt(Integer::intValue).sum();
        int h1i=0,h2i=0,h3i=0;
        System.out.println("---- "+sumS1+" "+sumS2+" "+sumS3);
        
        Integer min = Math.min(Math.min(sumS1, sumS2),sumS3);
        
        while(sumS1.compareTo(sumS2)!=0 || sumS2.compareTo(sumS3)!=0 || sumS3.compareTo(sumS1)!=0) {
            if(sumS1.compareTo(min)>0) {
                sumS1-=h1.get(h1i);
                h1i++;
            }
            else {
                min = sumS1; 
            }
            //min = sumS1;
            if(sumS2.compareTo(min)>0) {
                sumS2-=h2.get(h2i);
                h2i++;
            }
            else {
                min = sumS2;
            }
            if(sumS3.compareTo(min)>0) {
                sumS3-=h3.get(h3i);
                h3i++;
            }
            else {
                min = sumS3;
            }
        }        
        return min;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n1 = Integer.parseInt(firstMultipleInput[0]);

        int n2 = Integer.parseInt(firstMultipleInput[1]);

        int n3 = Integer.parseInt(firstMultipleInput[2]);

        List<Integer> h1 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h2 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> h3 = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        int result = Result.equalStacks(h1, h2, h3);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
