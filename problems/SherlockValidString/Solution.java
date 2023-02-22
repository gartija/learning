

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
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
    // Write your code here
        Map <Character,Integer> charCount= new HashMap<>();
        Map <Integer,Integer> histogram = new HashMap<>();        
        for(char c : s.toCharArray()) {            
            Character cChar = Character.valueOf(c);
            Integer storedCount = charCount.getOrDefault(cChar,0);
            charCount.put(cChar, storedCount+1);
            var toSubstract = histogram.get(storedCount);
            var toSum = histogram.getOrDefault(storedCount+1,0);
            if(toSubstract!=null) {
                if(toSubstract-1==0) {
                    histogram.remove(storedCount);
                }
                else {
                    histogram.put(storedCount, toSubstract-1);
                }
                
            }
            histogram.put(storedCount+1, toSum+1);
            
        }
        Set<Integer> histogramKeys = histogram.keySet();
        if(histogramKeys.size()>2) {
            return "NO";
        }
        else if(histogramKeys.size()==1) {
            return "YES";
        }
        else {
            Object []histogramKeysArray=histogramKeys.toArray();
            int diff = (Integer)histogramKeysArray[0] - (Integer)histogramKeysArray[1];
            if(((Integer)histogramKeysArray[1]==1 && histogram.get(histogramKeysArray[1])==1) || ((Integer)histogramKeysArray[0]==1 && histogram.get(histogramKeysArray[0])==1)) {
                return "YES";
            }
            else if(diff == -1 && histogram.get(histogramKeysArray[1])==1) {
                return "YES";
            }
            else if(diff == 1 && histogram.get(histogramKeysArray[0])==1) {
                return "YES";
            }
            else {
                return "NO";
            }
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
