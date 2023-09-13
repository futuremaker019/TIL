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
