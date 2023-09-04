import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DepthFirstSearch2 {

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

        bfs(R);

        bw.close();
        br.close();
    }

    private static void bfs(int idx) {

    }

}
