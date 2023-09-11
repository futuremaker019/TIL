import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DFS_BFS {

    /**
     * DFS 와 BFS 표현
     * 정점의 번호가 가장 적은 것을 먼저 방문한다.
     * 정점은 1번부터 N번까지이다.
     *
     * N : 정점(노드)의 수
     * M : 간선의 수
     * V : 시작점
     */

    final static int MAX = 1000 + 10;
    static boolean graph[][];
    static boolean visited[];
    static ArrayList<Integer> queue;
    static int N, M, V;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        visited = new boolean[MAX];
        graph = new boolean[MAX][MAX];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        dfs(V);
        System.out.println();

        bfs(V);
    }

    private static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 1; i <= N; i++) {
            // visited가 true이고, 해당하는 i번째가 true라면
            if (visited[i] == false && graph[v][i] == true) {
                // 해당하는 번째의 인덱스를 넣어주면 된다.
                dfs(i);
            }
        }
    }

    private static void bfs(int v) {
        queue = new ArrayList<>();
        visited = new boolean[MAX];

        queue.add(v);
        visited[v] = true;

        while (!queue.isEmpty()) {
            int value = queue.remove(0);
            System.out.print(value + " ");

            for (int i = 1; i <= N; i++) {
                if (visited[i] == false && graph[value][i] == true) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    /**

     4 5 1
     1 2
     1 3
     1 4
     2 4
     3 4

     * */
}
