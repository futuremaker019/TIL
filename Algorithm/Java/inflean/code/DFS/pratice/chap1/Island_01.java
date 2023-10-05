package pratice.chap1;

import java.io.*;
import java.util.StringTokenizer;

public class Island_01 {

    /**
     * 섬과 바다, 섬의 개수를 세는 프로그램을 작성
     *  가로, 세로, 대각선으로 연결되어 있는 사각형은 모두 하나의 섬으로 인식한다.
     *  땅은 1 (true), 바다는 0 (false)
     */

    static int MAX = 50;
    static boolean[][] map;
    static int N, M;
    static int[] dirY = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dirX = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) {
                break;
            }

            map = new boolean[MAX][MAX];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()) == 1;
                }
            }

            int count = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (map[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }


            bw.write(String.valueOf(count));
            bw.newLine();
        }

        br.close();
        bw.close();
    }

    private static void dfs(int y, int x) {
        map[y][x] = false;

        for (int i = 0; i < dirY.length; i++) {
            int newY = y + dirY[i];
            int newX = x + dirX[i];

            if (map[newY][newX]) {
                dfs(newY, newX);
            }
        }
    }

    /**

     1 1
     0
     2 2
     0 1
     1 0
     3 2
     1 1 1
     1 1 1
     5 4
     1 0 1 0 0
     1 0 0 0 0
     1 0 1 0 1
     1 0 0 1 0
     5 4
     1 1 1 0 1
     1 0 1 0 1
     1 0 1 0 1
     1 0 1 1 1
     5 5
     1 0 1 0 1
     0 0 0 0 0
     1 0 1 0 1
     0 0 0 0 0
     1 0 1 0 1
     0 0

     0
     1
     1
     3
     1
     9

     */

}
