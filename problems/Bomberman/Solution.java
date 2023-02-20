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
     * Complete the 'bomberMan' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING_ARRAY grid
     */

    public static List<String> bomberMan(int n, List<String> grid) {
    // Write your code here
        int i=2;
        System.out.println("Resp");
        if(n<2) {
            return grid;
        }
        else if(n%2==0) {
            n=2;    
        }
        else if(n%4==1){
            n=5;
        }
        else {
            n=3;
        }

        char [][]tempGrid = new char[grid.size()][];
        for(int j = 0; j<grid.size();j++) {
            tempGrid[j]=grid.get(j).toCharArray();
        }
        while(i<=n) {
            if(n==i) {
                System.out.println("i==n");
                char []lineofOs = new char[grid.get(0).length()];
                Arrays.fill(lineofOs,'O');
                Arrays.fill(tempGrid,lineofOs);
                return matrixToGrid(tempGrid);
            }
            else {
                char [][]tempGridp = new char[grid.size()][];
                char []lineofOs = new char[grid.get(0).length()];
                Arrays.fill(lineofOs,'O');
                for (int j = 0; j<grid.size();j++) {
                    tempGridp[j]=lineofOs.clone();  
                }
                for(int j = 0; j < tempGrid.length ; j++) {
                    for(int k= 0; k< tempGrid[j].length; k++) {
                        if(tempGrid[j][k]=='O') {
                            tempGridp[j][k] = '.';
                            if(j>0) tempGridp[j-1][k] = '.';
                            if(j<tempGrid.length-1) tempGridp[j+1][k] = '.';
                            if(k>0) tempGridp[j][k-1] = '.';
                            if(k<tempGrid[j].length-1) tempGridp[j][k+1] = '.';
                        }
                    }
                }
                tempGrid = tempGridp;
                if(n==i+1) {
                    System.out.println("i+1==n");
                    return matrixToGrid(tempGrid);
                }
                else {
                    i+=2;
                }
            }
            
        }
        return matrixToGrid(tempGrid);
    }

    private static List<String> matrixToGrid(char[][] matrix) {
        List<String> ret = new ArrayList<>();
        for(int i=0;i<matrix.length;i++) {
            StringBuffer buf = new StringBuffer();
            for(int j=0;j<matrix[i].length;j++) {
                buf.append(matrix[i][j]);
            }
            //System.out.println(buf);
            ret.add(buf.toString());
        }
        return ret;
    }

    /* Estudiar esta solución. 
    
public static List<String> bomberMan(int n, List<String> grid) {
        if (n < 2) return grid;
        String fullRow = "O".repeat(grid.get(0).length());
        List<String> fullGrid = IntStream.range(0, grid.size())
                .mapToObj(i -> fullRow)
                .collect(Collectors.toList());
        grid = doBoom(fullGrid, grid);
        List<String> grid2 = doBoom(fullGrid, grid);
        return n % 2 == 0 ? fullGrid : n % 4 == 1 ? grid2 : grid;
    }

    private static List<String> doBoom(List<String> fullGrid, List<String> grid) {
        int length = grid.get(0).length();
        int height = grid.size();
        List<StringBuilder> builders = fullGrid.stream().
                map(StringBuilder::new).
                collect(Collectors.toList());

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < length; j++) {
                if (grid.get(i).charAt(j) == 'O') {
                    builders.get(i).setCharAt(j, '.');
                    if (j != 0) builders.get(i).setCharAt(j - 1, '.');
                    if (j < length - 1) builders.get(i).setCharAt(j + 1, '.');
                    if (i != 0) builders.get(i - 1).setCharAt(j, '.');
                    if (i < height - 1) builders.get(i + 1).setCharAt(j, '.');
                }
            }
        }
        return builders.stream()
                .map(StringBuilder::toString)
                .collect(Collectors.toList());
    }    */

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int r = Integer.parseInt(firstMultipleInput[0]);

        int c = Integer.parseInt(firstMultipleInput[1]);

        int n = Integer.parseInt(firstMultipleInput[2]);

        List<String> grid = IntStream.range(0, r).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        List<String> result = Result.bomberMan(n, grid);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

