package pratice.chap1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TreeParent_01 {

    static int MAX = 100_000;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int [] answer;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList[MAX];
        visited = new boolean[MAX];
        answer = new int[MAX];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        dfs(1);

        for (int i = 2; i <= N; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void dfs(int idx) {
        visited[idx] = true;

        for (int i = 0; i < graph[idx].size(); i++) {
            int value = graph[idx].get(i);
            if (!visited[value]) {
                answer[value] = idx;   // key : childId, value : parentId
                dfs(value);
            }
        }
    }


    /**

     7
     1 6
     6 3
     3 5
     4 1
     2 4
     4 7

     result

     4
     6
     1
     3
     1
     4

     12
     1 2
     1 3
     2 4
     3 5
     3 6
     4 7
     4 8
     5 9
     5 10
     6 11
     6 12

     result

     1
     1
     2
     3
     3
     4
     4
     5
     5
     6
     6

     */

}
