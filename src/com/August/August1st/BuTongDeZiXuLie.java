package com.August.August1st;
//动态规划篇
public class BuTongDeZiXuLie {
    public static int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        int res = 0;
        for(int i = 0; i <= s.length(); i ++){
            dp[i][0] = 1;
        }
        for(int i = 1; i <= s.length(); i ++){
            for(int j = 1; j <= t.length(); j ++){
                if(s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j];
                res = Math.max(dp[i][j],res);
            }
        }
        return dp[s.length()][t.length()];
    }
    public static void main(String[] args) {
        String s = "rabbbit";
        String t = "rabbit";
        System.out.println(numDistinct(s, t));
    }
}
