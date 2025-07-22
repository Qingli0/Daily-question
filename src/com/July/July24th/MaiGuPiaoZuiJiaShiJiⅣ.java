package com.July.July24th;
//动态规划
//买股票最佳时机Ⅳ

public class MaiGuPiaoZuiJiaShiJiⅣ {
    public static int maxProfit(int k, int[] prices) {
        int [][]dp = new int[prices.length][2 * k + 1];
        for(int i = 0; i < 2 * k + 1; i ++){
            if(i % 2 == 0) dp[0][i] = 0;
            else dp[0][i] = - prices[0];
        }
        for(int i = 1; i < prices.length; i ++){
            dp[i][0] = dp[i - 1][0];
            for(int j = 1; j < 2 * k + 1; j ++){
                if(j % 2 != 0)//买入
                    dp[i][j] = Math.max(dp[i - 1][j - 1] - prices[i],dp[i - 1][j]);
                else dp[i][j] = Math.max(dp[i - 1][j - 1] + prices[i],dp[i - 1][j]);//卖出
            }
        }
        return dp[prices.length - 1][2 * k];
    }
    public static void main(String[] args) {
        int[] prices = {2,4,1};
        int k = 2;
        System.out.println(maxProfit(k,prices));
    }
}
