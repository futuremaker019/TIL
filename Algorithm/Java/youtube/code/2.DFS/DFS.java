import java.util.ArrayList;
import java.util.List;

public class DFS {
    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        graph.add(new ArrayList<>());
        graph.add(new ArrayList<>(List.of(2, 3, 8)));
        graph.add(new ArrayList<>(List.of(1, 7)));
        graph.add(new ArrayList<>(List.of(1, 4, 5)));
        graph.add(new ArrayList<>(List.of(3, 5)));
        graph.add(new ArrayList<>(List.of(3, 4)));
        graph.add(new ArrayList<>(List.of(7)));
        graph.add(new ArrayList<>(List.of(2, 6, 8)));
        graph.add(new ArrayList<>(List.of(1, 7)));

        boolean[] visited = new boolean[10];

        dfs(graph, 1, visited);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph, int id, boolean[] visited) {
        /**
         * 1. visited -> true
         * 2. graph에서 해당하는 인덱스 번쨰의 값을 찾아 깊이우선탐색을 실행
         */
        visited[id] = true;
        System.out.print(id + " ");

        for (int v : graph.get(id)) {
            if (visited[v] == false) {
                dfs(graph, v, visited);
            }
        }
    }
}
