import java.io.*;
import java.util.StringTokenizer;

public class NumberOfRelatives {

    /**
     * 촌수계산하기
     * 1. 부모와 자식 사이의 관계를 1촌으로 정의
     * 2. 나와 아버지, 아버지와 할아버지는 1촌, 나와 할아버지는 2촌, 나와 아버지 형제들은 3촌
     * 3. 부모 자식간의 관계가 주어졌을때 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하라.
     * <p>
     * 간선을 세는 문제인가...
     * 간선이 1이면 1촌, 간선이 2면 2촌 ---
     *
     * N :
     * M :
     * start : 시작 간선
     * end : 도착 간선
     */

    final static int MAX = 100 + 10;
    static boolean[][] graph;
    static boolean[] visited;
    static int N, M, start, end, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        graph = new boolean[MAX][MAX];
        visited = new boolean[MAX];
        answer = -1;

        // 간선 표시함
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        dfs(start, 0);

        // 출력
        bw.write(String.valueOf(answer));

        bw.close();
        br.close();
    }

    private static void dfs(int idx, int count) {
        visited[idx] = true;
        if (idx == end) {
            answer = count;
            return;
        }
        System.out.println("count = " + count);
        System.out.print(idx + " ");

        // 모든 노드를 돌게 만들어준다.
        for (int i = 1; i <= N; i++) {
            if (visited[i] == false && graph[idx][i] == true) {
                dfs(i, count + 1);
            }
        }
    }

    /**
     *
     * N : 9
     * M : 7
     * start : 7
     * end : 3
     *
      9 7 7 3
      1 2
      1 3
      2 7
      2 8
      2 9
      4 5
      4 6
     *
     */
}
