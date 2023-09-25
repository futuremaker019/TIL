package pratice;

import java.io.*;
import java.util.StringTokenizer;

public class pp {

    final static int MAX = 50;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new char[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = st.nextToken().charAt(0);
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        br.close();
        bw.close();
    }

    /**

     5 6
     101010
     101111
     100010
     011100
     110100

     */
}
