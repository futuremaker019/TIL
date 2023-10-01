package pratice.chap1;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS_BFS {

    /**
     * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
     * 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다.
     * 정점 번호는 1부터 N까지이다.
     *
     *
     */

    static int MAX = 1_000;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int N, M, V;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[MAX];
        visited = new boolean[MAX];
        answer = new int[MAX];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        dfs(V);

        ArrayList<Integer> queue = new ArrayList<>();
        visited = new boolean[MAX];
        bfs(V, queue);
    }

    private static void bfs(int idx, ArrayList<Integer> queue) {
        visited[idx] = true;
        queue.add(idx);

        while (!queue.isEmpty()) {

        }
    }

    private static void dfs(int idx) {
        visited[idx] = true;
        for (int i = 0; i < graph[idx].size(); i++) {
            int value = graph[idx].get(i);
        }

    }
}
