package com.July.July5th;
//动态规划
//整数拆分

/**
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 *
 * 返回 你可以获得的最大乘积 。
 */
public class ZhengShuChaiFen {
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        for(int i = 3; i <= n; i ++){
            for(int j = 1; j < i ; j ++){
                dp[i] = Math.max(dp[i],Math.max(dp[i - j],i - j) * j);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(integerBreak(n));
    }
}
