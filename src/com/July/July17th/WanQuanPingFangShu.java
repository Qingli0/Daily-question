package com.July.July17th;
//动态规划篇
//完全平方数

import java.util.Arrays;

/**
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 *
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 */
/*
   这道题将n开算数平方根，可以将其转换成硬币问题，有1 ~ 根号n取整的硬币，问最少的硬币组成
 */
public class WanQuanPingFangShu {
    public static int numSquares(int n) {
        int coinSize = (int)Math.sqrt(n);
        int[] dp = new int[n + 1];
        Arrays.fill(dp,Integer.MAX_VALUE);
        int[] coins = new int[coinSize];
        for(int i = 0; i < coinSize; i ++){
            coins[i] = (int)Math.pow(i + 1, 2);
        }
        for(int i = 0; i < coinSize; i ++){
            for(int j = coins[i]; j <= n; j ++){
                if(j == coins[i]) dp[j] = 1;
                if(dp[j - coins[i]] != Integer.MAX_VALUE && dp[coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j - coins[i]] + dp[coins[i]],dp[j]);

            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(numSquares(n));
    }
}
