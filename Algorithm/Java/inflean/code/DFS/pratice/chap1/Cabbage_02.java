package pratice.chap1;

import java.io.*;
import java.util.StringTokenizer;

public class Cabbage_02 {

    static int MAX = 50 + 10;
    static boolean[][] map;
    static int T; // 테스트 케이스 개수
    static int M; // 배추 가로길이
    static int N; // 배추 세로길이
    static int C; // 배추의 갯수

    static int[] dirY = {-1, 1, 0, 0};
    static int[] dirX = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        while(T-- != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new boolean[MAX][MAX];

            for (int i = 0; i < C; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = true;
            }

            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
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
        for (int i = 0; i < 4; i++) {
            int newY = y + dirY[i];
            int newX = x + dirX[i];

            if (newY >= 0 && newY < N && newX >= 0 && newX < M) {
                if (map[newY][newX]) {
                    dfs(newY, newX);
                }
            }

        }
    }
}