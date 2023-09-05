import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class DepthFirstSearch2 {

    /**
     * N개의 정점과 M개의 간선으로 구성된 무방향 그래프가 주어진다. 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
     * 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하라. 인접 정점온 내림차순으로 방문한다.
     */

    static int MAX = 1000000;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int N, M, R;
    static int[] answer;
    static int order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        /**
         * 작업순서
         * 0. graph 초기화, visited 초기화
         * 1. 노드의 수를 받아 노드를 생성한다.
         * 2. 간선의 수를 받아 간선을 생성한다.
         * 3. 간선을 내림차순으로 정렬한다.
         */
        graph = new ArrayList[MAX];
        visited = new boolean[MAX];
        answer = new int[MAX];
        order = 1;

        // 노드 초기화
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 간선 초기화
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i], Comparator.reverseOrder());
        }

        dfs(R);

        for (int i = 0; i < N; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }

        bw.close();
        br.close();
    }

    /**
     * 5 5 1
     * 1 4
     * 1 2
     * 2 3
     * 2 4
     * 3 4
     */

    /**
     * 순서대로 인덱스를 넣어준다.
     */
    private static void dfs(int idx) {
        visited[idx] = true;          // 해당 배열의 인덱스에 방문을 남김
        answer[idx] = order++;        // answer의 인덱스 번쨰에 순서를 의미하는 order를 저장한다.
                                      // order를 하나 증가시킨다.

        for (int i = 0; i < graph[idx].size(); i++) {
            /**  인덱스는 1식 증가하므로 해당 인덱스의 다음 노드를 방문하기 위해 graph[idx].get(i)를 하여 순회시킨다. */
            Integer nextIdx = graph[idx].get(i);
            if (!visited[nextIdx]) {
                dfs(nextIdx);
            }
        }
    }
}
