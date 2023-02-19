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
     * Complete the 'caesarCipher' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    public static String caesarCipher(String s, int k) {
    // Write your code here
        StringBuffer buf = new StringBuffer();
        k=k%26;
        for(char a : s.toCharArray()) {
            boolean isUpper = Character.isUpperCase(a);
            int nValue = Character.getNumericValue(a);//10 through 35
            if(nValue>=10 && nValue<=35) {
                if(nValue+k>35) {
                    nValue = 9 + nValue-35+k;
                }
                else {
                    nValue += k;
                } 
                char c = Character.forDigit(nValue,36);
                
                if(isUpper) {
                    c = Character.toUpperCase(c);
                }
                buf.append(c);
            }
            else {
                buf.append(a);
            }
        }
        return buf.toString();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int k = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.caesarCipher(s, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
