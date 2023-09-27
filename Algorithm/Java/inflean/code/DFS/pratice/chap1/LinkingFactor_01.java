package pratice.chap1;

import java.io.*;
import java.util.StringTokenizer;

public class LinkingFactor_01 {

    /**
     *  연결 요소의 개수
     *      한마디로 dfs가 몇번 도는지 파악하는 문제
     *  입력
     *  1. 정점의 개수 N과 간선의 수 M
     *  2.
     */

    static int MAX = 1000;
    static boolean[][] graph;
    static boolean[] visited;
    static int N, M;

    private static void dfs(int idx) {
        visited[idx] = true;
        for (int i = 1; i <= N; i++) {
            if (graph[idx][i] && visited[i] == false) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i] == false) {
                count++;
                dfs(i);
            }
        }

        bw.write(String.valueOf(count));

        bw.close();
        br.close();

    }

    /**

     6 5
     1 2
     2 5
     5 1
     3 4
     4 6

     [
        []
        [2, 5]
        [3, 4, 5]
        [2, 4]
        [2, 3, 5, 6]
        [1, 2, 4]
        [4]
     ]

     6 8
     1 2
     2 5
     5 1
     3 4
     4 6
     5 4
     2 4
     2 3

     */

}
