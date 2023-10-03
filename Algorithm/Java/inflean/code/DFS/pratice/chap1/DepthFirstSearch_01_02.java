package pratice.chap1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DepthFirstSearch_01_02 {

    /**
     *
     * N 개의 정점, M개의 간선, 시작 노드 R
     *  깊이우선탐색의 기본적인 형태
     *  내림차순으로 정렬된다.
     *
     */
    static int MAX = 100_000;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int N, M, R;
    static int[] answer;
    static int order = 1;

    private static void dfs(int idx) {
        visited[idx] = true;
        answer[order++] = idx;

        for (int i = 0; i < graph[idx].size(); i++) {
            int value = graph[idx].get(i);
            if (!visited[value]) {
                dfs(value); // 깊이우선탐색을 수행하게 만든다.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new ArrayList[MAX];
        visited = new boolean[MAX];
        answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i], Comparator.reverseOrder());
        }

        answer[R] = R;
        dfs(R);

        for (int i = 1; i <= N; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
