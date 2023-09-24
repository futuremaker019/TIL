package pratice;

import java.io.*;
import java.util.StringTokenizer;

public class Island {

    /**
     * 섬은 1, 바다는 0
     *  중간의 섬을 기준으로 좌, 우, 대각선으로 섬이 연결되어 있음
     *      (포인트는 대각선 또한 신경을 써야 한다는 것)
     *  연결된 섬의 개수는 총 몇개인가
     *
     *  W, h : 가로, 세로 (1 <= w, h <= 50)
     */

    final static int MAX = 50;
    static boolean[][] map;
    static boolean[][] visited;
    // 상, 상우, 우, 우하, 하, 하좌, 좌, 좌상
    static int dirY[] = {1, 1, 0, -1, -1, -1, 0, 1};
    static int dirX[] = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N;   // Y축
    static int M;   // X축

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[MAX][MAX];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j - 1) == '1';
            }
        }

        // DFS 의 갯수를 세는 방법은 해당 DFS가 변환을 시작할때 센다.
        // 변환이 끝나면
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

     4 5

     10100
     10000
     10101
     10010

     10101
     10000
     10101
     10010

     1 0 1 0 0
     1 0 0 0 0
     1 0 1 0 1
     1 0 0 1 0
     */
}
