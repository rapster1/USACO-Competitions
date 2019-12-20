import java.io.*;
import java.util.*;

public class whereami {
    public static void main(String[] args) throws IOException {
        //input file and output file
        BufferedReader f = new BufferedReader(new FileReader("lineup.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lineup.out")));

        StringTokenizer st = new StringTokenizer(f.readLine());
        int n = Integer.parseInt(st.nextToken());
        String[] cows = new String[]{"Bessie", "Buttercup", "Belinda", "Beatrice", "Bella", "Blue", "Betsy", "Sue"};
        String[][] rules = new String[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(f.readLine());
            String s = st.nextToken();
            rules[i][0] = s;
            s = st.nextToken();
            s = st.nextToken();
            s = st.nextToken();
            s = st.nextToken();
            s = st.nextToken();
            rules[i][1] = s;
        }

        Map<String, List<String>> neighbours = new HashMap<>();
        for(String[] edge : rules) {
            String a = edge[0], b = edge[1];
            neighbours.computeIfAbsent(a, k -> new ArrayList()).add(b);
            neighbours.computeIfAbsent(b, k -> new ArrayList()).add(a);
        }

        List<String> endpoints = new ArrayList<>();
        for(String a : cows) {
            if(!neighbours.containsKey(a) || neighbours.get(a).size() <= 1) {
                endpoints.add(a);
            }
        }
        Collections.sort(endpoints);

        String[] out1 = new String[cows.length];
        Set<String> used = new HashSet<>();
        int i = 0;
        for(String a : endpoints) {
            if(used.contains(a)) { continue; }
            out1[i++] = a;
            used.add(a);
            while(neighbours.containsKey(a) && !neighbours.get(a).isEmpty()) {
                String b = neighbours.get(a).get(0);
                out1[i++] = b;
                used.add(b);
                neighbours.get(b).remove((String) a);
                a = b;
            }
        }
        for (String output : out1) {
            out.println(output);
        }

        out.close();
    }
}
