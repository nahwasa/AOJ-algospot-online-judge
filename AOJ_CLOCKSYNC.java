import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] MAPPING = {
            {0, 1, 2},
            {3, 7, 9, 11},
            {4, 10, 14, 15},
            {0, 4, 5, 6, 7},
            {6, 7, 8, 10, 12},
            {0, 2, 14, 15},
            {3, 14, 15},
            {4, 5, 7, 14, 15},
            {1, 2, 3, 4, 5},
            {3, 4, 5, 9, 13}
    };
    int[] arr;
    int min;

    private boolean chk() {
        for (int i = 0; i < 16; i++) {
            if (arr[i] != 12) return false;
        }
        return true;
    }

    private void pressSwitch(int mappingIdx, int nth, boolean isCw) {
        for (int i = 0; i < MAPPING[mappingIdx].length; i++) {
            if (isCw) {
                arr[MAPPING[mappingIdx][i]] += 3 * nth;
                if (arr[MAPPING[mappingIdx][i]] > 12)
                    arr[MAPPING[mappingIdx][i]] -= 12;
            } else {
                arr[MAPPING[mappingIdx][i]] -= 3 * nth;
                if (arr[MAPPING[mappingIdx][i]] <= 0)
                    arr[MAPPING[mappingIdx][i]] += 12;
            }
        }
    }

    private void solve(int cnt, int mappingIdx) {
        if (mappingIdx == 10) {
            if (min > cnt && chk()) {
                min = cnt;
            }
            return;
        }

        solve(cnt, mappingIdx+1);
        for (int i = 1; i <= 3; i++) {
            pressSwitch(mappingIdx, i, true);
            solve(cnt+i, mappingIdx+1);
            pressSwitch(mappingIdx, i, false);
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (tc-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            min = Integer.MAX_VALUE;
            arr = new int[16];
            for (int i = 0; i < 16; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            solve(0, 0);
            sb.append(min==Integer.MAX_VALUE ? -1 : min).append('\n');
        }
        System.out.print(sb);
    }

    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
