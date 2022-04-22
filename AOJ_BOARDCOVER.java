import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int dr[][] = {{1, 1, 0, 0}, {1, 1, 1, 1}};
    private static final int dc[][] = {{0, 0, 1, 1}, {-1, 1, 1, 0}};
    int r, c, remain;
    boolean[][] arr;
    int cnt;

    private void rec(int idx, int sr) {
        if (remain == idx) {
            cnt++;
            return;
        }
        for (int i = sr; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                if (!arr[i][j]) {
                    for (int dir = 0; dir < 4; dir++) {
                        int nr1 = i+dr[0][dir];
                        int nc1 = j+dc[0][dir];
                        int nr2 = i+dr[1][dir];
                        int nc2 = j+dc[1][dir];
                        if (arr[nr1][nc1]||arr[nr2][nc2]) continue;

                        arr[nr1][nc1] = true;
                        arr[nr2][nc2] = true;
                        arr[i][j] = true;
                        rec(idx+1, i);
                        arr[nr1][nc1] = false;
                        arr[nr2][nc2] = false;
                        arr[i][j] = false;
                    }
                    return;
                }
            }
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            remain = 0;
            cnt = 0;
            arr = new boolean[r+2][c+2];
            for (int i = 0; i < arr.length; i++) {
                Arrays.fill(arr[i], true);
            }
            for (int i = 1; i <= r; i++) {
                String s = br.readLine();
                for (int j = 1; j <= c; j++) {
                    arr[i][j] = s.charAt(j-1) == '#' ? true : false;
                    if (!arr[i][j])
                        remain++;
                }
            }
            if (remain%3!=0) {
                sb.append(0).append('\n');
                continue;
            }
            remain /= 3;
            rec(0, 0);
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
