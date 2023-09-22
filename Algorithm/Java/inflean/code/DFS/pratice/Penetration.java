package pratice;

import java.io.*;
import java.util.StringTokenizer;

public class Penetration {

    final static int MAX = 1000 + 10;
    static boolean[][] map;
    static boolean[][] visited;
    static int[] dirX = {-1, 1, 0, 0};
    static int[] dirY = {0, 0, -1, 1};
    static int T;
    static int N;       // Y 좌표
    static int M;       // X 좌표
    static int K;
    static boolean answer;

    private static boolean dfs(int y, int x) {
        if (y != 0 && y == N) {
            return true;
        }

        map[y][x] = false;
        for (int i = 0; i < 4; i++) {
            int newY = y + dirY[i];
            int newX = x + dirX[i];
            if (map[newY][newX]) {
                dfs(newY, newX);
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[MAX][MAX];

        /**
         map에서는 (1, 1) 부터 시작을 해야하며 str에서 읽어올 값은 첫 char 부터이므로
         for는 1부터 시작한다.
         */
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; i++) {
                map[i][j] = str.charAt(j - 1) == '1';
            }
        }

        for (int i = 1; i <= M; i++) {
            if (map[1][i])
                dfs(1, i);
        }

        if(answer)
            bw.write("YES");
        else
            bw.write("NO");

        bw.close();
        br.close();
    }

    /**

     5 6
     101010
     101111
     100010
     011100
     110100

     5 6
     101010
     101111
     100010
     111100
     110100

     */
}
