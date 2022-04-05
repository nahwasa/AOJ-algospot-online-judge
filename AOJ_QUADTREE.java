import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    String s = null;
    int idx = 0;
    private String proc() {
        if (s.charAt(idx) != 'x') {
            return "" + s.charAt(idx++);
        }
        idx++;
        String res1 = proc();
        String res2 = proc();
        String res3 = proc();
        String res4 = proc();
        return 'x'+res3+res4+res1+res2;
    }
    private void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (c-->0) {
            s = br.readLine();
            idx = 0;
            sb.append(proc()).append('\n');
        }
        System.out.print(sb);
    }
    public static void main(String[] args) throws Exception {
        new Main().solution();
    }
}
