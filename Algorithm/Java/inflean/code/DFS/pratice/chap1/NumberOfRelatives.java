package pratice.chap1;

import java.io.*;
import java.util.StringTokenizer;

public class NumberOfRelatives {

    static int MAX = 100;
    static boolean[][] graph;
    static boolean[] visited;
    static int N;       // 전체 사람의 수
    static int M;       // 간선의 수
    static int s, e;    // 촌수 계산을 위한 서로 다른 수
    static int answer;

    private static void dfs(int idx, int count) {
        visited[idx] = true;
        if (idx == e) {
            answer = count;
            return;     // return 시, e와 같은 idx를 넘겨버린다. 그때에 count를 저장할 방법을 생각해야한다.
        }

        for (int i = 1; i <= N; i++) {
            if (graph[idx][i] && visited[i] == false) {
                dfs(i, count + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        graph = new boolean[MAX][MAX];
        visited = new boolean[MAX];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        answer = -1;
        dfs(s, 0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    /**

     9
     7 3
     7
     1 2
     1 3
     2 7
     2 8
     2 9
     4 5
     4 6

     출력 : 3

     9
     8 6
     7
     1 2
     1 3
     2 7
     2 8
     2 9
     4 5
     4 6

     출력 : -1

     */

}
