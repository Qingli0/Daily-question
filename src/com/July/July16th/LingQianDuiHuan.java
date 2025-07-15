package com.July.July16th;
//动态规划
//零钱兑换

import java.util.Arrays;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 *
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 *
 * 你可以认为每种硬币的数量是无限的。
 */
public class LingQianDuiHuan {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE); // 初始化为“无穷大”
        Arrays.sort(coins);
        dp[0] = -456;
        for(int i = 0; i < coins.length; i ++){
            for(int j = coins[i]; j <= amount; j ++){
                if(coins[i] == j) dp[j] = 1;
                else if(dp[j - coins[i]] != Integer.MAX_VALUE && dp[coins[i]] != Integer.MAX_VALUE)
                    dp[j] = Math.min(dp[j],dp[j - coins[i]] + dp[coins[i]]);
            }
        }
        if(dp[amount] < 0 || dp[amount] == Integer.MAX_VALUE) return -1;
        return dp[amount];
    }
    public static void main(String arg[]){
        int[] coins = {186,419,83,408};
        int amount = 6249;
        System.out.println(coinChange(coins, amount));
    }
}
