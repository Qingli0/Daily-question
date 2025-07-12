package com.July.July13th;
//动态规划
//零钱兑换Ⅱ

/**
 *
 */
public class LingQianDuiHuanⅡ {
    public static int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount + 1];
        for(int i = 0; i <= amount; i ++){
            if(i % coins[0] == 0) dp[0][i] = 1;
        }
        for(int i = 1; i < coins.length; i ++){
            for(int j = 0; j <= amount; j ++){
                if(coins[i] <= j){
                    dp[i][j] = dp[i][j - coins[i]] + dp[i - 1][j];
                }else dp[i][j] = dp[i - 1][j];
            }
        }

        return dp[coins.length - 1][amount];
    }
    //滚动数组方法求解
    public static int change2(int amount, int[] coins) {
        //采用一维数组 递推公式dp[j] += dp[j - coins[i]]
        int[] dp = new int[amount + 1];
        dp[0]  = 1;
        for(int i = 0; i < coins.length; i ++){
            for(int j = coins[i]; j <= amount; j ++){
                    dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }
    public static void main(String[] args) {
        int amount = 4681;
        int coins[] = {10,1};
        System.out.println(change2(amount, coins));
        System.out.println(change(amount, coins));
    }
}
