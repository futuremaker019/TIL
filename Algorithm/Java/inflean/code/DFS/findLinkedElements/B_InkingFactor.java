package findLinkedElements;

import java.io.*;
import java.util.StringTokenizer;

public class B_InkingFactor {

    /**
     * 연결 요소의 개수
     * 백준 11724
     */


    static boolean[][] graph;
    static boolean[] visited;
    static int N, M;        // N - 노드수, M - 간선수
    static int answer;

    public static void main(String[] args) throws IOException {
        // 0. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new boolean[N + 1][N + 1];  // 시작노드를 1로 작성하므로 +1을 해준다.
        visited = new boolean[N + 1];

        // 1. graph 에 연결 정보 채우기
        int x, y;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
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

        for (int i = 1; i <= N; i++) {
            if (visited[i]) {

            }
        }
    }
}
