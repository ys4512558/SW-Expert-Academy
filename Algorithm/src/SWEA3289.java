import java.io.*;
import java.util.StringTokenizer;

public class SWEA3289 {
    static int[] parent;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            sb.append(solve()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String solve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            makeSet(i);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(stk.nextToken());
            int a = Integer.parseInt(stk.nextToken());
            int b = Integer.parseInt(stk.nextToken());

            if(command == 0){
                union(a, b);
                continue;
            }
            stringBuilder.append(findSet(a) == findSet(b) ? 1 : 0);
        }
        return stringBuilder.toString();
    }

    private static void union(int a, int b) {
        int repA = findSet(a);
        int repB = findSet(b);
        if (repA == repB) return;
        parent[repB] = repA;
    }

    private static int findSet(int a) {
        if(parent[a] == a) return a;
        return parent[a] = findSet(parent[a]);
    }

    private static void makeSet(int current) {
        parent[current] = current;
    }
}
