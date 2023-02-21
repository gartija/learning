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
     * Complete the 'minimumBribes' function below.
     *
     * The function accepts INTEGER_ARRAY q as parameter.
     */

    public static void minimumBribes(List<Integer> q) {
        int bribes = 0; 
        for(int i = q.size(); i>0;i--) {
            int index = q.indexOf(Integer.valueOf(i));
            System.out.println("i "+i+" index "+index); 
            if(i-(index+1)>=3) {
                System.out.println("Too chaotic"); 
                return; 
            }
            bribes+=i-(index+1);
            q.remove(index);
        }
        
        /*for(int i = 0; i<q.size(); i++) {
            if(i+1<q.size()) {
                int diff = q.get(i)-q.get(i+1);
                if(diff>=3) {
                    System.out.println("Too chaotic"); 
                    return;   
                }
                else if(diff<3 && diff>0) {
                    bribes+=diff;    
                }
            }
            /*if(i+1-q.get(i)<=-3) {
                System.out.println("Too chaotic");
                return;
            }
            else if(i+1-q.get(i)<0) {
                bribes+=Math.abs(i+1-q.get(i));
            }
            else if(i+1<q.size() && q.get(i)>q.get(i+1)) {
                bribes++;
            }
        }*/
        System.out.println(bribes);
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                Result.minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
