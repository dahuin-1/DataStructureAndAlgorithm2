package Grade4.Algorithm.week6.dp;


public class example4 {
    static int N = 6; //행렬개수
    static int[] d = {5,2,3,4,6,7,8}; // 행열의 행, 열의 개수 (1번 행렬의 행의 수: d[0], 열의 수: d[1])

    public static void main(String args[]) throws Exception {
        System.out.println(solve());
    }

    public static int solve() {
        int[][] M = new int[N+1][N+1];

        for (int len = 1; len < N; len++) {		// len: i~j의 길이(j-i)
            for (int i = 1; i <= N-len; i++) {
                int j = i + len;
                M[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = M[i][k] + M[k + 1][j] + d[i-1] * d[k] * d[j];
                    M[i][j] = Math.min(M[i][j], cost);
                }
            }
        }

        return M[1][N];
    }

}


