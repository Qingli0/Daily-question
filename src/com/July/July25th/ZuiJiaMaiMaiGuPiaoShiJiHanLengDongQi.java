package com.July.July25th;
//动态规划
//最佳买卖股票时机含冷冻期

/**
 * 这道题和之前的购买股票不同的地方就是多了一个冷冻期，这就多了一个状态
 * dp[i][0]  买入的状态，持有股票的状态
 * dp[i][1]  没有股票的状态，也就是这个时候我账户没有股票，但是我不是冷冻期,可以购入股票
 * dp[i][2]  这天卖出股票
 * dp[i][3]  冷冻期
 */
public class ZuiJiaMaiMaiGuPiaoShiJiHanLengDongQi {
    public static int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        for(int i = 1; i < prices.length; i ++){
            dp[i][0] = Math.max(dp[i - 1][0],Math.max(dp[i - 1][3] - prices[i],dp[i - 1][1] - prices[i]));
            dp[i][1] = Math.max(dp[i - 1][1],dp[i - 1][3]);
            dp[i][2] = dp[i - 1][0] + prices[i];
            dp[i][3] = dp[i - 1][2];
        }
        return Math.max(Math.max(dp[prices.length - 1][0],dp[prices.length - 1][1]),Math.max(dp[prices.length - 1][2],dp[prices.length - 1][3]));
    }
    public static void main(String[] args) {
        int[] prices = {1};
        System.out.println(maxProfit(prices));
    }
}
