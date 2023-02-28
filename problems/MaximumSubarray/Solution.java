package problems.MaximumSubarray;

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
     * Complete the 'maxSubarray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> maxSubarray(List<Integer> arr) {
    // Write your code here
        int max = Collections.max(arr);
        List<Integer> ret = new ArrayList<>(2);
        if(max<=0) {
            ret.add(max);
            ret.add(max);
        }
        else {
            Integer subArrAcum = 0;
            Integer subSeqAcum = arr.parallelStream().filter(a -> a>0).mapToInt(Integer::intValue).sum();
            int iMax = 0, sumIMax = 0, valIAct;
            for(int i = 0;i<arr.size();i++) {
                valIAct = arr.get(i);
                sumIMax += valIAct; //arr.subList(iMax, i+1).parallelStream().mapToInt(Integer::intValue).sum();
                if(valIAct>=sumIMax) {
                    iMax = i;
                    sumIMax = valIAct;
                }
                if(sumIMax>subArrAcum) {
                    subArrAcum=sumIMax;
                }
            }
            ret.add(subArrAcum);
            ret.add(subSeqAcum);
        }
        return ret;

    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                List<Integer> result = Result.maxSubarray(arr);

                bufferedWriter.write(
                    result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                    + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
