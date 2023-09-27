package pratice.chap1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class DepthFirstSearch_01 {

    /**
     * 입력
     *  1. 정점의 수 N, 간선의 수 M, 시작 정점 R
     *  2. M개 줄에 간선 정보 u,v가 주어짐
     *
     * 출력
     *  방문한 정점을 출력한다.
     */

    static int MAX = 100_000;
    static ArrayList<Integer>[] graph;
    static ArrayList<ArrayList<Integer>> graph1;
    static boolean[] visited;
    static int N, M, R;
    static int[] answer;
    static int order = 1;

    private static void dfs(int idx) {
        visited[idx] = true;
        answer[idx] = order;
        order++;

        for (int i = 0; i < graph[idx].size(); i++) {
            int value = graph[idx].get(i);
            if (visited[value] == false) {
                dfs(value);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new ArrayList[MAX];
        visited = new boolean[MAX];
        answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x].add(y);
            graph[y].add(x);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        dfs(R);

        for (int i = 1; i <= N; i++) {
            bw.write(String.valueOf(answer[i]));
            bw.newLine();
        }

        br.close();
        bw.close();

//        for (int i = 1; i <= N; i++) {
//            graph1.add(new ArrayList<>());
//        }
//
//        for (int i = 1; i <= M; i++) {
//            st = new StringTokenizer(br.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
//            graph1.get(y).add(x);
//            graph1.get(x).add(y);
//        }


    }
}
