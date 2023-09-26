package findSameCategories;

import java.io.*;
import java.util.StringTokenizer;

public class E_JumpingJelly {

    /**
     * - 젤리의 출발점은 항상 가장 왼쪽, 가장 위의 칸이다.
     * - 젤리가 이동 가능한 방향은 오른쪽과 아래 뿐이다.
     * - 젤리가 가장 오른쪽 가장 아래칸에 도착한다면 젤리의 승리이다.
     * - 젤리가 한번에 이동할 수 있는 칸의 수는 현제 밟고 있는 칸에 쓰여 있는 수 만큼이다.
     * <p>
     * 입력
     * - 첫번째 입력은 게임 구역의 크기 N (2 <= N <= 3)이 주어진다.
     * - 두번째 부터 마지막 입력까지 게임판의 구역(맵)이 주어진다.
     * - 게임판에 승리 지점에는 -1이 쓰여있고, 나머지 칸에는 0이상 100 이하의 정수가 쓰여있다.
     * <p>
     * 출력
     * - 젤리가 끝에 도달하면 "HaruHaru", 도달할수 없으면 "Hing"을 한줄에 출력한다.
     */
    // N은 최대 3이며 100까지 점프가 가능하므로 3 + 100, 여유분 10을 더함,
    // 어차피 map의 N, N 위치를 알아내는 것이므로 여유분을 더해도 상관없다.
    final static int MAX = 3 + 100 + 10;
    static int[][] map;
    static boolean[][] visited;
    static int dirY[] = {1, 0};
    static int dirX[] = {0, 1};
    static int N;   // y축

    private static void dfs(int y, int x) {
        int cur = map[y][x];
        map[y][x] = 0;
        if (y == N && x == N)
            return;

        for (int i = 0; i < 2; i++) {
            int newY = y + dirY[i] * cur;
            int newX = x + dirX[i] * cur;
            if (cur != 0)
                dfs(newY, newX);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        map = new int[MAX][MAX];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1);

        if (map[N][N] == 0)
            bw.write("HaruHaru");
        else
            bw.write("Hing");

        br.close();
        bw.close();
    }

    /**
     * 정리
     *
     * 1. 오른쪽과 아래로만 이동 가능, (1, 1)에서 (N, N)으로 이동 -> DFS, BFS
     * 2. 서로 연결되었다는 정보를 어떻게 하나의 자료구조로 통합할까? (2차원 배열)
     * 3. 이미 방문한 지점을 다시 방문하지 않으려면 어떤 자료구조를 사용해야 될까?
     * 4. visited 배열을 생략할 수는 없을까
     * 5. 어느 지점에서 dfs를 시작할까
     * 6. 어느 방향으로 dfs를 진행할까
     */
}
