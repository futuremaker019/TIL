import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreeParent {

    /**
     * 루트없는 트리가 주어짐
     * 이때, 트리의 루트를 1이라고 정했을때, 각 노드의 부모를 구하는 프로그램을 작성하라.
     *
     * N : 노드의 개수 (2 <= N <= 100,000)
     */

    static int MAX = 100_000 + 10;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int N, M;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[MAX];
        visited = new boolean[MAX];
        answer = new int[MAX];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int x, y;
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        dfs(1);

        for (int i = 2; i <= N; i++) {
            bw.write(String.format("parent : %d, child: %d", answer[i], i));
            bw.newLine();
        }

        // Map은 중복을 허용하지 않기 떄문에 사용하지 못한다. (하나의 부모에 여러개의 자식을 가질수 있기 때문에)
//        Map<Integer, Integer> relations = new HashMap<>();
//        for (Map.Entry<Integer, Integer> entry : relations.entrySet()) {
//            String format = String.format("parent : %d , child : %d", entry.getKey(), entry.getValue());
//            System.out.println("format = " + format);
//        }

        br.close();
        bw.close();
    }

    private static void dfs(int parent) {
        visited[parent] = true;

        for (int i = 0; i < graph[parent].size(); i++) {
            int value = graph[parent].get(i);
            if (visited[value] == false) {
                answer[value] = parent;
                dfs(value);
            }
        }
    }

    /**

     7 6
     1 6
     6 3
     3 5
     4 1
     2 4
     4 7

     */
}
