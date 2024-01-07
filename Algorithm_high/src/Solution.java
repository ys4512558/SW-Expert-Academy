import java.io.*;
import java.util.*;

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

    }

    /**
     * SWEA: 1868. 파빙파빙 지뢰찾기
     * @throws IOException
     */

    private static void prob1868() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#" + i + " ");
            sb.append(solve1868()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve1868() throws IOException {
        int N = Integer.parseInt(br.readLine());
        char map[][] = new char[N][N];
        int res[][] = new int[N][N];

        boolean visit[][] = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = input.charAt(j);
                if(c == '*'){
                    visit[i][j] = true;
                }
                map[i][j] = c;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '*') {
                    res[i][j] = -1;
                } else{
                    res[i][j] = calcArea(i, j, map);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (res[i][j] == 0 && !visit[i][j]) {
                    visit = bfs1868(i, j, res, visit);
                    cnt++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visit[i][j]) {
                    cnt++;
                }
            }
        }


        return cnt;
    }

    private static boolean[][] bfs1868(int row, int col, int[][] res, boolean[][] visit) {
        int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int dx[] = {-1, 0, 1, 1, -1, 1, -1, 0, 1};

        visit[row][col] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int point[] = queue.poll();
            int curY = point[0];
            int curX = point[1];

            for (int i = 0; i < 8; i++) {
                int y = curY + dy[i];
                int x = curX + dx[i];
                if (y < 0 || y >= visit.length || x < 0 || x >= visit[y].length) {
                    continue;
                }
                if(visit[y][x]){
                    continue;
                }
                if (res[y][x] == 0) {
                    queue.add(new int[]{y, x});
                }
                visit[y][x] = true;
            }

        }

        return visit;
    }
    private static int calcArea(int row, int col, char map[][]){
        int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
        int dx[] = {-1, 0, 1, 1, -1, 1, -1, 0, 1};
        int cnt = 0;
        for (int i = 0; i < 8; i++) {
            int y = row + dy[i];
            int x = col + dx[i];
            if (y < 0 || y >= map.length || x < 0 || x >= map[y].length) {
                continue;
            }
            if (map[y][x] == '*') {
                cnt++;
            }
        }

        return cnt;
    }


    /**
     * swea: 4193. 수영대회 결승전 ( 완전 탐색 + 구현 )
     * @throws IOException
     */
    private static void prob4193() throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) {
            sb.append("#" + i + " ");
            sb.append(solve4193()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve4193() throws IOException {
        class Point{
            int row;
            int col;
            int time;

            public Point(int row, int col, int time) {
                this.row = row;
                this.col = col;
                this.time = time;
            }
        }

        int N = Integer.parseInt(br.readLine());
        int map[][] = new int[N][N];
        boolean visit[][] = new boolean[N][N];

        int dx[] = {-1, 1, 0, 0};
        int dy[] = {0, 0, -1, 1};

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int start[] = new int[2];
        int end[] = new int[2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        end[0] = Integer.parseInt(st.nextToken());
        end[1] = Integer.parseInt(st.nextToken());

        //bfs탐색
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(start[0], start[1], 0));
        visit[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            int row = cur.row;
            int col = cur.col;
            int time = cur.time;

            if (row == end[0] && col == end[1]) {
                return time;
            }

            for (int i = 0; i < 4; i++) {
                int x = col + dx[i];
                int y = row + dy[i];

                if (x < 0 || x >= N || y < 0 || y >= N || map[y][x] == 1) {
                    continue;
                }
                if (map[y][x] == 2 && time % 3 != 2) {
                    queue.add(new Point(row, col, time + 1));
                    continue;
                }
                if(!visit[y][x]){
                    queue.add(new Point(y, x, time + 1));
                    visit[y][x] = true;
                }
            }
        }

        return -1;
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