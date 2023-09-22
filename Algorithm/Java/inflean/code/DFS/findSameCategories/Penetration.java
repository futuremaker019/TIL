package findSameCategories;

import java.io.*;
import java.util.Arrays;
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

    private static void dfs(int y, int x) {
        if (y == N) {
            answer =  true;
            return;
        }

//        visited[y][x] = true;
        map[y][x] = false;
        for (int i = 0; i < 4; i++) {
            int newX = x + dirX[i];
            int newY = y + dirY[i];
//            if (map[newY][newX] && !visited[newY][newX])
            if (map[newY][newX])
                dfs(newY, newX);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[MAX][MAX];
//        visited = new boolean[MAX][MAX];

        // 한줄의 값을 모두 받고 character 별로 boolean 값을 넣어준다.
        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = (str.charAt(j - 1) == '1') ? true : false;
                System.out.print(str.charAt(j - 1) + " ");
            }
            System.out.println();
        }

        // y의 첫번째줄이 true 인것을 확인하고 dfs 를 진행한다.
        // dfs 에서 y == N 일때 answer 를 true 로 반환해준다.
        for (int i = 1; i <= M; i++) {
            if (map[1][i])
                dfs(1, i);
        }

        if (answer)
            bw.write("YES");
        else
            bw.write("NO");

        bw.close();
        br.close();
    }

    /**

     입력시 한줄씩 넣어야 들어간다.... tockenizer 가 아니라서...
     5 6
     101010
     101111
     100010
     011100
     110100
        result : NO

     5 6
     101010
     101111
     100010
     111100
     110100
        result : YES

     8 8
     0 0 1 1 1 0 0 0
     1 0 0 1 1 1 1 1
     1 1 1 0 0 1 1 0
     0 0 1 1 0 1 1 1
     0 1 1 1 0 1 1 0
     0 1 0 0 0 0 1 1
     1 0 1 0 1 1 1 1
     1 1 1 1 0 1 0 0

     8 8
     1 1 0 0 0 1 1 1
     0 1 1 0 0 0 0 0
     0 0 0 1 1 0 0 1
     1 1 0 0 1 0 0 0
     1 0 0 0 1 0 0 1
     1 0 1 1 1 1 0 0
     0 1 0 1 0 0 0 0
     0 0 0 0 1 0 1 1

     */
}
