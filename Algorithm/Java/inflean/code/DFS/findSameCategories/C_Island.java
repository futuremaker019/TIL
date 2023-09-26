package findSameCategories;

import java.io.*;
import java.util.StringTokenizer;

public class C_Island {

    /**
     * 섬의 개수
     * 백준 4963
     * 
     * 섬은 1, 바다는 0
     *  중간의 섬을 기준으로 좌, 우, 대각선으로 섬이 연결되어 있음
     *      (포인트는 대각선 또한 신경을 써야 한다는 것)
     *  연결된 섬의 개수는 총 몇개인가
     *
     *  W, h : 가로, 세로 (1 <= w, h <= 50)
     *
     *  입력
     *  - 입력은 여러개의 테스트 케이스로 이루어져있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
     *  - 둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다
     *  - 입력의 마지막 줄에는 0이 두개 주어진다.
     */

    final static int MAX = 50 + 10;
    static boolean map[][];
    static boolean visited[][];
    static int dirY[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dirX[] = {0, 1, 1, 1, 0, -1, -1, -1};
    static int N;
    static int M;

    private static void dfs(int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < 8; i++) {
            int newY = y + dirY[i];
            int newX = x + dirX[i];
            if (map[newY][newX] && visited[newY][newX] == false) {
                dfs(newY, newX);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            map = new boolean[MAX][MAX];
            visited = new boolean[MAX][MAX];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken()) == 1;
                }
            }

            int count = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (map[i][j] && !visited[i][j]) {
                        dfs(i, j);
                        count++;
                    }
                }
            }

            bw.write(String.valueOf(count));
            bw.newLine();
        }

        bw.close();
        br.close();
    }
}
