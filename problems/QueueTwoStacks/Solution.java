package problems.QueueTwoStacks;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int nQueries = Integer.parseInt(bufferedReader.readLine().trim());
        List<String[]> list = new ArrayList<>();

        IntStream.range(0, nQueries).forEach(tItr -> {
            try {
                list.add(bufferedReader.readLine().trim().split(" "));                    
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        bufferedReader.close();

        printQueueOps(list);
        //bufferedWriter.close();
    }

    private static void printQueueOps(List <String[]>ops) {
        List<String> stack = new ArrayList<>();
        for(String []currOp : ops) {
            if(currOp[0].equals("1")) {
                stack.add(currOp[1]);
            }
            else if(currOp[0].equals("2") && stack.size()>0) {
                stack.remove(0);
            }
            else if(currOp[0].equals("3") && stack.size()>0) {
                System.out.println(stack.get(0));
            }
        }
    }
}
