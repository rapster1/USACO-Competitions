import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class gymnastics {
    static BigInteger factorial(int N)
    {
        // Initialize result
        BigInteger f = new BigInteger("1"); // Or BigInteger.ONE

        // Multiply f with 2, 3, ...N
        for (int i = 2; i <= N; i++)
            f = f.multiply(BigInteger.valueOf(i));

        return f;
    }

    static String check(int n1, int n2, int[] trial) {
        if (indexOfIntArray(trial, n1) < indexOfIntArray(trial, n2)) {
            return "less";
        } else {
            return "more";
        }
    }

    public static int indexOfIntArray(int[] array, int key) {
        int returnvalue = -1;
        for (int i = 0; i < array.length; ++i) {
            if (key == array[i]) {
                returnvalue = i;
                break;
            }
        }
        return returnvalue;
    }

    public static boolean contains(String[] array, String key) {
        for (String element : array) {
            if (element == key) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        //input file and output file
        BufferedReader f = new BufferedReader(new FileReader("gymnastics.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gymnastics.out")));

        //reads in and initializes n and k values
        StringTokenizer st = new StringTokenizer(f.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        //total number of possible pairs (nC2)
        int numberPairs = (factorial(n).divide(factorial(n-2).multiply(BigInteger.valueOf(2)))).intValue();
               // (2 * factorial(n-2)));
        //array of ordered arrays containing data from one cow practice session
        int[][] trials = new int[k][n];
        //array of arrays containing each pair (2 elements each)
        int[][] pairs = new int[numberPairs][2];

        //initializes trials array
        for(int i = 0; i < k; i++){
            st = new StringTokenizer(f.readLine());
            for(int a = 0; a < n; a++) {
                trials[i][a] = Integer.parseInt(st.nextToken());
            }
        }

        //initializes pairs array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int l = i + 1; l < n; l++) {
                int[] pair = {trials[0][i], trials[0][l]};
                pairs[index] = pair;
                index = index + 1;
            }
        }
        System.out.println();
        //test function, subtracts 1 from total number of pairs each time a pair is found to be inconsistent
        for (int i = 0; i < pairs.length; i++) {
            index = 0;
            String[] checkArray = new String[k];
            for (int a = 0; a < trials.length; a++) {
                checkArray[index] = check(pairs[i][0], pairs[i][1], trials[a]);
                index = index + 1;
            }

            if (checkArray[0] == "less") {
                if (contains(checkArray, "more")) {
                    numberPairs = numberPairs - 1;
                }
            }

            if (checkArray[0] == "more") {
                if (contains(checkArray, "less")) {
                    numberPairs = numberPairs - 1;
                }
            }
        }

        out.print(numberPairs);
        out.close();
    }
}
