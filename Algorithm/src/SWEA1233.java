import java.io.*;
import java.util.StringTokenizer;

public class SWEA1233 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static char[] opers;
    public static void main(String[] args) throws IOException {
        int T = 10;

        for (int i = 1; i <= T; i++) {
            sb.append("#").append(i).append(" ");
            sb.append(solve()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int solve() throws IOException {
        int N = Integer.parseInt(br.readLine());
        opers = new char[N + 1];
        int res = -1;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
                if (st.countTokens() == 0) { //수 or 연산자 하나만 받은 경우
                    res = 0;
                } else {
                    opers[idx] = str.charAt(0);
                }
            } else {
                if (st.countTokens() == 0) {
                    opers[idx] = ' ';
                } else {
                    res = 0;
                }
            }
        }
        if (res == 0) {
            return 0;
        }
        return check(N);
    }

    private static int check(int N) {
        for (int i = N; i >= 1; i--) {
            //자식 노드가 없으면 다음 반복
            if(i * 2 > N) continue;
            if(i * 2 + 1 > N) continue;
            //만약 현재 노드가 자식을 가지고 있는 상태이며 연산자인 경우
            //자식 노드가 연산자이면 유효하지 않다.
            //뒤에서 부터 탐색하기 때문에 현재 노드가 무조건 연산자 이면 안되도록 로직 구성
            if (opers[i] == ' ' || opers[i * 2] != ' ' || opers[i * 2 + 1] != ' ') {
                return 0;
            }
            //위에 조건에 맞지 않으면 수식을 계산한 결과를 현재 노드에 저장한 것처럼 표현하기 위해 
            //더 이상 연산자가 아닌 것처럼 표현
            opers[i] = ' ';
        }
        return 1;
    }
}