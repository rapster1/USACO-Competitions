import java.io.*;
import java.util.*;

public class lineup {

    public static HashSet<String> subSequences = new HashSet<String>();

    static int numberOccurrences(String str, String word) {
        int lastIndex = 0;
        int count = 0;

        while(lastIndex != -1){

            lastIndex = str.indexOf(word,lastIndex);

            if(lastIndex != -1){
                count ++;
                lastIndex += word.length();
            }
        }
        return count;
    }

    public static void subsequence(String str, int n) {
        for (int i = 0; i < n; i++)
            for (int j = i+1; j <= n; j++)
                subSequences.add(str.substring(i, j));
    }

    public static void main(String[] args) throws IOException {
        //input file and output file
        BufferedReader f = new BufferedReader(new FileReader("whereami.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("whereami.out")));

        //reads in and initializes n and k values
        StringTokenizer st = new StringTokenizer(f.readLine());
        int stringSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(f.readLine());
        //initializes variable for input string
        String string = st.nextToken();

        //computes all subsequences and adds to set
        subsequence(string, string.length());

        boolean[] checks= new boolean[stringSize];

        int smallestSize = 0;

        for (int i = 0; i < stringSize; i++) {
            checks[i] = true;
            for (String s : subSequences) {
                if (s.length() == i + 1 && numberOccurrences(string, s) != 1) {
                        checks[s.length() - 1] = false;
                }
            }
            if (checks[i] == true) {
                smallestSize = i + 1;
                break;
            }
        }

        out.println(smallestSize);
        out.close();
    }
}
