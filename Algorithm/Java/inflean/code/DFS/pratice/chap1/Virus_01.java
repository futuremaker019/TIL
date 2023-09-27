package pratice.chap1;

import java.io.*;
import java.util.StringTokenizer;

public class Virus_01 {

    /**
     *  입력
     *  1. 컴퓨터의 수
     *  2. 네트워크 상에 연결되어 있는 컴퓨터의 쌍의 수
     */
    static int MAX = 100;
    static boolean[][] map;
    static boolean[] visited;
    static int N;   // 간선의 수
    static int M;
    static int count;

    /**
     * dfs 에서는 하위의 자식을 어떻게 알고 찾아갈까?
     *  간선의 수 만큼
     *  무슨 컨셉으로 2차원 맵을 돌면서 dfs를 확인하는걸까
     */
    private static void dfs(int idx) {
        visited[idx] = true;


        for (int i = 1; i <= N; i++) {
            if (map[i][idx] && visited[i] == false) {
                count++;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = true;
            map[y][x] = true;
        }

        dfs(1);

        bw.write(String.valueOf(count));

        br.close();
        bw.close();
    }

    /**

     7
     6
     1 2
     2 3
     1 5
     5 2
     5 6
     4 7
     6 7

     */
}
