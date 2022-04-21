import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    int n, cnt, v;
    ArrayList<Integer>[] friend;

    private void init(int num) {
        n = num;
        friend = new ArrayList[n];
        for (int i = 0; i < n; i++)
            friend[i] = new ArrayList<>();
        cnt = v = 0;
    }

    private void rec(int idx, int ud) {
        if (idx == n/2) {
            cnt++;
            return;
        }
        for (int i = ud; i < n; i++) {
            if ((v&(1<<i))!=0) continue;
            v|=1<<i;
            ArrayList<Integer> cur = friend[i];
            for (int j = 0; j < cur.size(); j++) {
                int next = cur.get(j);
                if (next < i) continue;
                if ((v&(1<<next))!=0) continue;
                v|=1<<next;
                rec(idx+1, i+1);
                v^=1<<next;
            }
            v^=1<<i;
        }
    }

    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (c-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            init(Integer.parseInt(st.nextToken()));
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                friend[a].add(b);
                friend[b].add(a);
            }
            boolean validChk = true;
            for (int i = 0; i < n; i++) {
                if (friend[i].isEmpty()) {
                    validChk = false;
                    break;
                }
            }
            if (validChk)
                rec(0, 0);
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
