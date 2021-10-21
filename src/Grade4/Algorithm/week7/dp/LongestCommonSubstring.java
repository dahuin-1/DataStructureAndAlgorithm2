package Grade4.Algorithm.week7.dp;

public class LongestCommonSubstring {

    //DP
    public int longestCommonSubsequenceDP(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1.charAt(i -1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m];
    }

    //Recursive
    int longestCommonSubsequenceRecursive(String text1, String text2, int n, int m) {
        if (n == 0 || m == 0)
            return 0;

        if (text1.charAt(n - 1) == text2.charAt(m - 1))
            return 1 + longestCommonSubsequenceRecursive(text1, text2, n - 1, m - 1);

        return Math.max(longestCommonSubsequenceRecursive(text1, text2, n - 1, m), longestCommonSubsequenceRecursive(text1, text2, n, m - 1));
    }

    //main method 호출용
    public int solveLCS(String text1, String text2) {
        return this.longestCommonSubsequenceRecursive(text1, text2, text1.length(), text2.length());
    }

    public static void main(String[] args) {
        LongestCommonSubstring lcs = new LongestCommonSubstring();
        String text1 = "abcbdab";
        String text2 = "bdcaba";
        System.out.println("-------------DP-------------------");
        int longestLength = lcs.longestCommonSubsequenceDP(text1, text2);
        System.out.println("Longest Substring Length ---> " + longestLength);
        System.out.println("-------------Recursive-------------");
        longestLength = lcs.solveLCS(text1, text2);
        System.out.println("Longest Substring Length ---> " + longestLength);
    }

}
