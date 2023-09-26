package findSameCategories;

import java.io.*;
import java.util.StringTokenizer;

public class A_Cabbage {

    /**
     * 유기농 배추
     *  백준 (1012)
     *
        1. 인접한 배추 상하좌우로 네 방향으로 이동이 가능Ma함
        2. 총 필요한 지렁이의 개수를 구하라.

        N, M : 가로, 세로 (1 <= N, M <= 50)
        K : 배추가 심어져 있는 위치의 개수 (1 <= K <= 2500)
     */

    /**
        풀이
            1. 배추의 위치가 확인이 된다면(해당 위치가 true라면) 해당위치의 상하좌우를 확인하여
                배추가 있다면 2차원 배열로 정의된 visited에 true를 표시한다.
            2. 세로 N, 가로 M으로 이중 for문을 구성하여 dfs를 돌아 해당하는 dfs가 몇번이 돌았는지 확인 후
                answer를 출력한다.
     */

    final static int MAX = 50 + 10;
    static boolean[][] map;
    static boolean[][] visited;
    static int T;       // 이건 뭐지? 이건 서로다른 배추를 놓고 테스트하는 테스테케이스 개수라고 한다.
    static int N;       // 세로
    static int M;       // 가로
    static int K;       // 배추가 심어져있는 위치의 개수
    static int[] dirY = {-1, 1, 0, 0};
    static int[] dirX = {0, 0, 1, -1};

    private static void dfs(int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
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

        // 테스트케이스란다. 그리고 각각의 다른 케이스(배추가 다르게 심어져있는 상태)를 테스트하기 위함이란다.
        // 한마디로 의미없는 작업
//        T = Integer.parseInt(br.readLine());

//        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new boolean[MAX][MAX];
            visited = new boolean[MAX][MAX];

            /** map 정보 입력 */
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                // y와 x의 위치를 변경했음
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                map[y + 1][x + 1] = true;

                /**
                 * map[y][x] = true;
                 * 위에서는 x자리에 0, y자리에 0 의 위치에 값을 넣지 않기위해 각 자리에 +1을 했지만
                 * 굳이 저 방식이 필요 없다면 map[y][x]를 입력하여도 무방할듯하다.
                 * 어차피 그래프 자체도 1부터 시작할 거니깐
                  */
            }

            /**  dfs 수행 */
            int answer = 0  ;

            // 2중 for문에서 N(Y축)을 기준으로 M(X축)을 순번데로 돌아야한다.
            // x의 0, y의 0에는 값이 없으므로 1부터 시작하는 인덱스를 나타내준다.
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (map[i][j] && visited[i][j] == false) {
                        answer++;
                        dfs(i, j);
                    }
                }
            }

            System.out.println("answer = " + answer);

            bw.write(String.valueOf(answer));
            bw.newLine();
//        }
    }

    /**

     2

     7 7 8
     1 1
     1 2
     2 1
     3 5
     4 5
     5 3
     5 4
     6 5

     */
}
