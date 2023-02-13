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
     * Complete the 'towerBreakers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     */

    public static int towerBreakers(int n, int m) {
        //Never understood the game rules. This code doesn't work as per the test cases. 
        /*I add the following code that works against the test cases, but I've no idea why. 
            if (n == 1) return 1;
            if (m == 1) return 2;
            return (n % 2 == 0) ?  2  :  1;
        */

    // Write your code here     
        if(n>1 && n%2==0)  {
            n = 2;
        }
        else if(n>1 && n%2!=0) {
            n = 3;
        }
        else {
            return 1;
        }
        int []towers = new int[n];
        for(int i = 0; i<n; i++){
            towers[i] = m;
        }

        int winner = 0, currentPlayer = 1;
        while(winner == 0) {

            Arrays.sort(towers);
            //System.out.println("CurP "+currentPlayer+"  Highest tower length "+towers[towers.length-1]+" Tower "+(towers.length-1));
            if(towers[towers.length-1]%2==0 && towers[towers.length-1]/2>=1) {
                towers[towers.length-1]=towers[towers.length-1]/2;
                //System.out.println("Lo parto por la mitad "+towers[towers.length-1]+" "+towers[0]);
            }
            else if(towers[towers.length-1]>1){
                //System.out.println("Lo reduzco a 1");
                if(towers.length==1) {
                    winner = currentPlayer;
                }
                else {
                    towers[towers.length-1] = 1;
                }
            }
            else {
                winner = currentPlayer==1?2:1;
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
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int n = Integer.parseInt(firstMultipleInput[0]);

                int m = Integer.parseInt(firstMultipleInput[1]);

                int result = Result.towerBreakers(n, m);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
