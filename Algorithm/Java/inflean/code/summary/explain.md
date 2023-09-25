graph를 생성하는 기준

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class NumberOfRelatives {

    static int SMALL_MAX = 1000 + 10;
    static int LARGE_MAX = 1000000 + 10;
    static boolean[][] arrayGraph;
    static ArrayList<Integer>[] listGraph;
    static boolean[] visited;
    static int N, M, V;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 노드의 수를 기준으로 graph를 생성할 때
        arrayGraph = new boolean[N + 1][N + 1];

        /**
         * MAX 값을 기준으로 graph를 생성할 때 
         *  MAX 값이 1000으로 작다면 boolean 2차 배열을 사용해도 무방하다.
         */
        arrayGraph = new boolean[SMALL_MAX][SMALL_MAX];

        /**
         * MAX 값을 기준으로 graph를 생성할 때
         *  MAX 값이 100000이 넘는 등 큰 값이 될때 ArrayList 배열을 고려해야 한다.
         */
        listGraph = new ArrayList[LARGE_MAX];
        for (int i = 0; i < N; i++) {
            listGraph[i] = new ArrayList<>();
        }

        // 간선의 수 만큼 배열을 채운다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            listGraph[x].add(y);
            listGraph[y].add(x);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(listGraph[i]);
        }
    }
}
```

DFS에서의 재귀에서 해야할 작업과 DFS를 끝내고 나올떄 결과를 담을 작업을 분리해야 한다.
```java
class Main {

    /**
     * dfs 실행부에서는 dfs 실행 후 결과를 확인하는 방식으로 진행한다.
     * 여기서는 하나의 dfs 실행을 통해 하나의 그룹/덩어리를 변경후 dfs가 종료되고 나오는 과정으로 진행한다.
     */
    public static void main(String[] args) {
        // ...

        int count = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (map[i][j] != 0) {
                    dfs(i, j);
                    count++;
                }
            }
        }
        
        // ...
    }

    /**
     *  dfs 구현부에서는 dfs 메서드 시작시 데이터를 변경하고 
     *  재귀를 통한 다음 dfs를 어떻게 갈지 정의한다. 
     */
    private static void dfs(int y, int x) {
        char value = map[y][x];
        map[y][x] = 0;

        if (value == '-' && map[y][x + 1] == '-')
            dfs(y, x + 1);
        if (value == '|' && map[y + 1][x] == '|')
            dfs(y + 1, x);
    }
}

```
