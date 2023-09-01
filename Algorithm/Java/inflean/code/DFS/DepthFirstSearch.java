import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DepthFirstSearch {

    final static int MAX = 1000000 + 10;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int N, M, R;
    static int[] answer;
    static int order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        R = Integer.parseInt(br.readLine());

    }


}
