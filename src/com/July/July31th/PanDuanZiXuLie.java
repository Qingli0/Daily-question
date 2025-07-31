package com.July.July31th;
//动态规划
//判断子序列

/**
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 */
public class PanDuanZiXuLie {
    public static boolean isSubsequence(String s, String t) {
        //这道题很简单啊，就是跟之前的数组最长子序列一样最后判断是否长度 == s.length()
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        int res = 0;
        for(int i = 1; i <= s.length(); i ++){
            for(int j = 1; j <= t.length();j ++){
                if(s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1] + 1;
                else dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1]);
                res = Math.max(dp[i][j],res);
            }
        }
        if(res == s.length()) return true;
        return false;
    }

    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }
}
