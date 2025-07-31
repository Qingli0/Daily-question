package com.August.August3rd;
//动态规划
//编辑距离

/**
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。
 *
 * 你可以对一个单词进行如下三种操作：
 *
 *     插入一个字符
 *     删除一个字符
 *     替换一个字符
 */
public class BianJiJvLi {
    public static int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i = 0; i <= word1.length(); i ++){
            dp[i][0] = i;
        }
        for(int i = 0; i <= word2.length(); i ++){
            dp[0][i] = i;
        }
        for(int i = 1; i <= word1.length(); i ++){
            for(int j = 1; j <= word2.length(); j ++){
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = Math.min(dp[i - 1][j] + 1,Math.min(dp[i][j - 1] + 1,dp[i - 1][j - 1] + 1));
            }
        }
        return dp[word1.length()][word2.length()];
    }
    public static void main(String[] args) {
        String word1 = "intention";
        String word2 = "execution";
        System.out.println(minDistance(word1, word2));
    }
}
