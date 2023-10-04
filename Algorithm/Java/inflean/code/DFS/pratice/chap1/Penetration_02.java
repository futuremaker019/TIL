package pratice.chap1;

import java.io.*;
import java.util.StringTokenizer;

public class Penetration_02 {

    /**
     * 섬유물질은 2차원 M * N의 격자로 이루어짐
     * 전류는 상하좌우로 움직임
     */

    static int MAX = 1_000 + 10;
    static boolean[][] graph;
    static int N, M;
    static int[] dirY = {-1, 1, 0, 0};
    static int[] dirX = {0, 0, -1, 1};
    static String answer = "NO";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new boolean[MAX][MAX];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken()) == 0;
            }
        }

        for (int i = 0; i < M; i++) {
            if (graph[0][i]) {
                dfs(0, i);
            }
        }

        bw.write(answer);

        br.close();
        bw.close();
    }

    private static void dfs(int y, int x) {
        graph[y][x] = false;

        if (y == N - 1) {
            answer = "YES";
        }

        for (int i = 0; i < 4; i++) {
            int newY = y + dirY[i];
            int newX = x + dirX[i];

            if (newY >= 0 && newY < N && newX >= 0 && newX < M) {
                if (graph[newY][newX]) {
                    dfs(newY, newX);
                }
            }
        }
    }

    /**

     5 6
     0 1 0 1 0 1
     0 1 0 0 0 0
     0 1 1 1 0 1
     1 0 0 0 1 1
     0 0 1 0 1 1

     result : NO

     8 8
     1 1 0 0 0 1 1 1
     0 1 1 0 0 0 0 0
     0 0 0 1 1 0 0 1
     1 1 0 0 1 0 0 0
     1 0 0 0 1 0 0 1
     1 0 1 1 1 1 0 0
     0 1 0 1 0 0 0 0
     0 0 0 0 1 0 1 1

     result : YES


     */
}
