package findSameCategories;

import java.io.*;
import java.util.StringTokenizer;

public class FloorDeco {

    /**
     * 바닥 장식 (백준 1388)
     *
     * - | 를 가진 나무판자
     * 두개의 '-' 가 인접해 있고, 같은 행에 있다면, 두개는 같은 나무 판자
     * 두개의 '|' 가 인접해 있고, 같은 열에 있다면, 두개는 같은 나무 판자
     *
     * 기훈이가 바닥을 장식하는데 필요한 나무 판자의 개수를 출력하는 프로그램을 작성하라
     *
     * 입력
     * - 첫째 줄에 방 바닥의 세로 크기 N과 가로크기 M이 주어진다.
     * - 둘째줄부터는 N개의 줄에 M개의 문자가 주어진다. '-', '|'
     * - N, M은 50 이하이다.
     */

    final static int MAX = 50 + 10;
    static char[][] map;
    static boolean[][] visited;

    private static void dfs(int y, int x) {
        char value = map[y][x];
        map[y][x] = 0;

        if (value == '-' && map[y][x + 1] == '-')
            dfs(y, x + 1);
        if (value == '|' && map[y + 1][x] == '|')
            dfs(y + 1, x);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[MAX][MAX];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j - 1);
            }
        }

        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] != 0) {
                    dfs(i, j);
                    count++;
                }
            }
        }

        bw.write(String.valueOf(count));

        bw.close();
        br.close();
    }
}
