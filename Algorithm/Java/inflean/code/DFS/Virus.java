import java.io.*;
import java.util.StringTokenizer;

public class Virus {
    static boolean[][] graph;
    static boolean[] visited;
    static int N, M;        // N - 노드수, M - 간선수
    static int answer;

    public static void main(String[] args) throws IOException {
        // 0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new boolean[N + 1][N + 1];  // 시작노드를 1로 작성하므로 +1을 해준다.
        visited = new boolean[N + 1];

        // 1. graph 에 연결 정보 채우기
        int x, y;
        for (int i = 0; i < M; i++) {

            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        // 2. dfs(재귀함수) 호출
        dfs(1);

        // 3. 출력
        bw.write(String.valueOf(answer - 1));   // 1번을 제외한 카운트

        br.close();
        bw.close();
    }

    private static void dfs(int idx) {
        visited[idx] = true;
        answer++;       // 1번 컴퓨터와 연결되있으므로 카운트를 올려준다.
        System.out.println("idx = " + idx);

        for (int i = 1; i <= N; i++) {
            if (visited[i] == false && graph[idx][i])
                dfs(i);
        }
    }

    /**
     * 1. 연결된 요소의 개수 -> DFS/BFS
     * 2. 서로 연결되었다는 정보를 어떻게 하나의 자료구조로 통합할까
     * 3. 이미 방문한 지점을 다시 방문하지 않으려면 어떤 자료구조를 사용해야 될까
     * 4. 어디에서 DFS를 시작할 것인가
     */
}
