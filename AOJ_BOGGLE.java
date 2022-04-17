import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static boolean chk = false;
    private static char[][] arr = new char[5][5];
    private static String dst = null;
    private static boolean[][][] v;

    private void search(int idx, int x, int y) {
        if (idx == dst.length()) {
            chk = true;
            return;
        }
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx==0 && dy==0) continue;
                if (chk)
                    return;
                int tX = x + dx;
                int tY = y + dy;
                if (tX < 0||tX >= 5||tY < 0||tY >= 5||v[tX][tY][idx])
                    continue;
                if (arr[tX][tY] == dst.charAt(idx)) {
                    v[tX][tY][idx] = true;
                    search(idx+1, tX, tY);
                }
            }
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-->0) {
            boolean[] existCharChk = new boolean[26];
            for (int i = 0; i < 5; i++) {
                String s = br.readLine();
                for (int j = 0; j < 5; j++) {
                    arr[i][j] = s.charAt(j);
                    existCharChk[arr[i][j]-'A'] = true;
                }
            }
            int n = Integer.parseInt(br.readLine());
            while (n-->0) {
                chk = false;
                dst = br.readLine();
                boolean existChk = true;
                for (int i = 0; i < dst.length(); i++) {
                    if (!existCharChk[dst.charAt(i)-'A']) {
                        existChk = false;
                        break;
                    }
                }
                if (existChk) {
                    v = new boolean[5][5][dst.length()];
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (!chk && arr[i][j] == dst.charAt(0))
                                search(1, i, j);
                        }
                    }
                }
                sb.append(String.format("%s %s\n", dst, chk?"YES":"NO"));
            }
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception{
        new Main().solution();
    }
}
