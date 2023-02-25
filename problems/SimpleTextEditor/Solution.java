package problems.SimpleTextEditor;

import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        

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
        printOps(list);
    }

    private static void printOps(List<String[]> ops) {
        Stack<String> history = new Stack<>();
        history.push("");
        for(String []currOp : ops) {
            if(currOp[0].equals("1")) {
                history.push(history.peek()+currOp[1]);
            }
            else if(currOp[0].equals("2")) {
                String str = history.peek();
                history.push(str.substring(0, str.length()-Integer.parseInt(currOp[1])));
                
            }
            else if(currOp[0].equals("3")) {
                System.out.println(history.peek().charAt(Integer.parseInt(currOp[1])-1));
            }
            else {
                history.pop();
            }
        }
    }
}