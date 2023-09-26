package findLinkedElements;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class C_DepthFirstSearch {

    /**
     * N개의 정점과 M개의 간선으로 구성된 무방향 그래프가 주어진다. 정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
     * 정점 R에서 시작하여 깊이 우선 탐색으로 노드를 방문할 경우 노드의 방문 순서를 출력하라. 인접 정점온 오름차순으로 방문한다.
     */
    final static int MAX = 1000000 + 10;    // 정점은 5<= N <= 1,000,000 이다
    static ArrayList<Integer>[] graph;      // ArrayList<Integer> 로 구성된 배열
    static boolean[] visited;               // 특정 노드의 인덱스에 방문했다는 확인을 위한 boolean 배열
    static int N;                           // 정점(노드)의 수
    static int M;                           // 간선의 수
    static int R;                           // 시작 노드 번호
    static int[] answer;                    //
    static int order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

        // graph 에 연결 정보 채우기
        graph = new ArrayList[MAX];         // graph 초기화
        for (int i = 1; i <= N; i++) {      // 정점의 수 만큼 ArrayList 생성
            graph[i] = new ArrayList<>();
        }
        visited = new boolean[MAX];
        answer = new int[MAX];
        order = 1;

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 1; i <= N; i++) {     // 각 정점에 들어온 ArrayList를 오름차순으로 정렬한다.
            Collections.sort(graph[i]);
        }

        dfs(R);

        /**
         *  answer에는 방문한 노드의 순서를 저장하므로
         *  dfs가 끝나고 저장된 answer를 차례대로 출력한다.??
         *
         *  정점 번호는 1번부터 N번이고 모든 간선의 가중치는 1이다.
         *      -> 1번부터 시작하고 가충치가 1이기떄문에 무조건 1부터 시작해 순서대로 쌓이는 구조이다.
         *      -> answer 를 고대로 출력해주면 답니다.
         */
        for (int i = 1; i <= N; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }

        bw.close();
        br.close();
    }

    private static void dfs(int idx) {
        visited[idx] = true;
        answer[idx] = order;
        order++;

        for (int i = 0; i < graph[idx].size(); i++) {
            int next = graph[idx].get(i);
            if (visited[next] == false) {
                dfs(next);
            }
        }
    }
}
