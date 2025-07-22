package com.July.July23th;
//动态规划篇
//买股篇的最佳时期Ⅲ
/**
 * 来分析一下这道题，运行两次买入卖出 dp[i][j] 表示第i天手头上的钱
 * dp[i][0] 表示不操作
 * dp[i][1] 表示第一次持有
 * dp[i][2] 表示第一次卖出之后的不持有
 * dp[i][3] 表示第二次持有
 * dp[i][4] 表示第二次卖出的不持有
 * dp[i][0] = dp[i - 1][0]
 * dp[i][1] = max(dp[i - 1][0] - price[i],dp[i - 1][1]) dp[i][1] 第一次持有由 前一个不持有卖掉price[i] 和 前一个不持有延续过来
 * dp[i][2] = max(dp[i - 1][1] + price[i],dp[i - 1][2]) dp[i][2] 第一次卖掉不持有由 前一个没有卖掉但是第i个price[i] 卖掉 和前一个也是卖掉不持有延续过来 取最大值
 * dp[i][3] = max(dp[i - 1][2] - price[i],dp[i - 1][3])
 * dp[i][4] = max(dp[i - 1][3] + price[i],dp[i - 1][4])
 */
public class MaiGuPiaoDeZuiJiaShiJiⅢ {
    public static int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][5];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;
        dp[0][3] = -prices[0];
        dp[0][4] = 0;
        for(int i = 1; i < prices.length; i ++){
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i],dp[i - 1][1]);
            dp[i][2] = Math.max(dp[i - 1][1] + prices[i],dp[i - 1][2]);
            dp[i][3] = Math.max(dp[i - 1][2] - prices[i],dp[i - 1][3]);
            dp[i][4] = Math.max(dp[i - 1][3] + prices[i],dp[i - 1][4]);
        }
        return dp[prices.length - 1][4];
    }
    public static void main(String[] args) {
        int[] prices = {1,2,3,4,5};
        System.out.println(maxProfit(prices));
    }
}
