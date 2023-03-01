package problems.QHeap1;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.util.stream.IntStream;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        List<String> inputs = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        scan.nextLine();
        IntStream.range(0, q).forEach(i->{
            String query = scan.nextLine();
            inputs.add(query);
        });
        runHeap(inputs);
    }

    private static void runHeap(List<String> queries) {
        //List<String> heap = new ArrayList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        queries.forEach(q -> {
            String[] subQuery = q.split(" ");
            switch(subQuery[0]) {
                case "1":
                    heap.add(Integer.parseInt(subQuery[1]));
                    break;
                case "2":
                    heap.remove(Integer.parseInt(subQuery[1]));
                    break;
                case "3":
                    System.out.println(heap.peek());
                    break;
            }
        });
 
    }
}