import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

    }

    private static void prob7465() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#" + i + " ");
            sb.append(solve7465()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve7465() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            map.get(A).add(B);
            map.get(B).add(A);
        }

        boolean isVisited[] = new boolean[N];
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            Queue<Integer> queue = new LinkedList<>();
            if (isVisited[i - 1]) {
                continue;
            }
            queue.add(i);

            while (!queue.isEmpty()) {
                int node = queue.poll();
                isVisited[node - 1] = true;
                ArrayList<Integer> temp = map.get(node);
                Collections.sort(temp);
                for (int j = 0; j < temp.size(); j++) {
                    if (!isVisited[temp.get(j) - 1]) {
                        queue.add(temp.get(j));
                    }
                }
            }
            cnt++;
        }
        return cnt;
    }
}