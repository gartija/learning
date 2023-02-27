package problems.TruckTour;

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
     * Complete the 'truckTour' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY petrolpumps as parameter.
     */

    public static int truckTour(List<List<Integer>> petrolpumps) {
    // Write your code here
        int posi = 0, j = 0, curAcum;
        int acum = 0;//petrolpumps.get(posi).get(0)-petrolpumps.get(posi).get(1);
        while(j+1!=posi) {
            curAcum = acum+petrolpumps.get(j).get(0)-petrolpumps.get(j).get(1);
            if(curAcum<0) {
                posi = posi + 1;
                acum = 0;//petrolpumps.get(posi).get(0)-petrolpumps.get(posi).get(1);
                j= posi;
            }
            else {
                acum = curAcum;
                j = j<petrolpumps.size()-1?j+1:0;
            }
            //System.out.println("Acum: "+acum+" j: "+j+" posi: "+posi+" curAcum: "+curAcum);
        }
        return posi;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> petrolpumps = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                petrolpumps.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.truckTour(petrolpumps);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
