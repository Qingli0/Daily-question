package com.July.July22th;
//动态规划
//买股票的最佳时机

/**
 *
 */
public class MaiGuPiaoDeZuiJiaShiJi {
    public static int maxProfit(int[] prices) {
        int[] profit = new int[prices.length - 1];
        int[] dp = new int[prices.length - 1];
        int maxProfit = 0;
        for(int i = 0; i < prices.length - 1; i ++){
            profit[i] = prices[i + 1] - prices[i];
        }

        //转换成了最大子序列和的动态规划
        dp[0] = profit[0];
        int res = dp[0];
        for(int i = 1; i < prices.length - 1; i ++){
            if(dp[i - 1] < 0) dp[i] = profit[i];
            else dp[i] = profit[i] + dp[i - 1];
            res = Math.max(dp[i],res);

        }

        for (int i = 0; i < profit.length; i++) {
            System.out.print(profit[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < profit.length; i++) {
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        if(res < 0) return 0;
        return res;
    }
    public static void main(String[] args) {
        int[] prices = {1,2};
        System.out.println(maxProfit(prices));
    }
}
