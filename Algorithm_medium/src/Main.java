import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

    }

    /**
     * SWEA 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기
     * @throws IOException
     */
    private static void prob1204() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#" + br.readLine() + " ");
            sb.append(solve1204()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve1204() throws IOException {
        HashMap<Integer, Integer> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0;
        int maxNum = 0;
        for (int i = 0; i < 1000; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (map.get(num) == null) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
            int tmp = map.get(num);
            if (max < tmp) {
                maxNum = num;
                max = map.get(num);
            } else if (max == tmp) {
                maxNum = Math.max(maxNum, num);
            }
        }

        return maxNum;
    }

    /**
     * SWEA 1959. 두 개의 숫자열
     * @throws IOException
     */
    private static void prob1959() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#" + i + " ");
            sb.append(solve1959()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve1959() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr1[] = new int[N];
        int arr2[] = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        int len = arr2.length - arr1.length;
        return len >= 0 ? calc1959(arr1, arr2) : calc1959(arr2, arr1);
    }
    //앞의 매개변수가 더 짧은 배열
    private static int calc1959(int[] array1, int[] array2) {
        int max = 0;
        for (int i = 0; i <= (array2.length - array1.length); i++) {
            int sum = 0;
            for (int j = 0; j < array1.length; j++) {
                sum += (array1[j] * array2[i + j]);
            }
            max = Math.max(max, sum);
        }

        return max;
    }


    /**
     * SWEA 1961. 숫자 배열 회전
     * @throws IOException
     */
    private static void prob1961() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            sb.append("#" + i + " ").append("\n");
            solve1961();
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void solve1961() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int arr[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            //90
            for (int j = 0; j < N; j++) {
                sb.append(arr[N - j - 1][i]);
            }
            sb.append(" ");
            //180
            for (int j = 0; j < N; j++) {
                sb.append(arr[N - i - 1][N - j - 1]);
            }
            sb.append(" ");
            //270
            for (int j = 0; j < N; j++) {
                sb.append(arr[j][N - i - 1]);
            }
            sb.append("\n");
        }
    }

    /**
     * SWEA 1974. 스도쿠 검증
     * @throws IOException
     */
    private static void prob1974() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int res = solve1974();
            sb.append("#" + i + " ").append(res).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve1974() throws IOException {
        int sudoku1[][] = new int[9][9];
        int sudoku2[][] = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                sudoku1[i][j] = Integer.parseInt(st.nextToken());
                sudoku2[j][i] = sudoku1[i][j];
            }
        }
        //가로 세로 확인
        for (int i = 0; i < 9; i++) {
            boolean isExist1[] = new boolean[9];
            boolean isExist2[] = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if(isExist1[sudoku1[i][j]-1]){
                    return 0;
                }
                if (isExist2[sudoku1[j][i]-1]) {
                    return 0;
                }
                isExist1[sudoku1[i][j]-1] = true;
                isExist2[sudoku1[j][i]-1] = true;
            }
        }

        //3x3 확인
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                Set<Integer> set = new HashSet<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        set.add(sudoku1[i + k][j + l]);
                    }
                }
                if (set.size() != 9) {
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * SWEA 12712. 파리퇴치3
     * @throws IOException
     */
    private static void prob12712() throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 1; i <= T; i++) {
            int res = solve12712();
            sb.append("#" + i + " ").append(res).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve12712() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int arr[][] = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum1 = arr[i][j]; // +
                int sum2 = arr[i][j]; // X

                //+ 모양일 때
                for (int k = 1; k < M; k++) {
                    if (j - k >= 0) {
                        sum1 += arr[i][j - k];
                    }
                    if (j + k < N) {
                        sum1 += arr[i][j + k];
                    }
                    if (i - k >= 0) {
                        sum1 += arr[i - k][j];
                    }
                    if (i + k < N) {
                        sum1 += arr[i + k][j];
                    }
                }
                //X 모양일 때
                for (int k = 1; k < M; k++) {
                    if (i - k >= 0) {
                        if (j - k >= 0) {
                            sum2 += arr[i - k][j - k];
                        }
                        if (j + k < N) {
                            sum2 += arr[i - k][j + k];
                        }
                    }
                    if (i + k < N) {
                        if (j - k >= 0) {
                            sum2 += arr[i + k][j - k];
                        }
                        if (j + k < N) {
                            sum2 += arr[i + k][j + k];
                        }
                    }
                }
                int tmp = Math.max(sum1, sum2);
                max = Math.max(max, tmp);
            }
        }
        return max;
    }
}
