package com.June.June19th;
//贪心篇
//买股票的最佳时机Ⅱ

/**
 * 给你一个整数数组，price[i] 表示某只股票第i天的价格
 * 在每一天，你可以决定是否购买或出售股票。你在任何时候最多只能持有 一股 股票。你在任何时候最多只能持有一股股票。
 * 你也可以先购买，然后再同一天出售
 * 返回能获得的最大利润
 */
public class MaiGuPiaoZuiJiaShiJiⅡ {
    public static int maxProfit(int[] prices) {
        //这道题设计的十分巧妙，因为每天的利润是可以拆解的
        /**
         * 比如第一天到第四天的利润 = prices[3] - prices[0] = prices[3] - prices[2] + prices[2] - price[1] + prices[1] - prices[0]
         * 相当于是将每天的利润相加了，因此我们可以直接用一个数组记录每天的获利情况，然后挑出正数获利相加
         */
        int[] profits = new int[prices.length - 1];
        for(int i = 0; i < prices.length - 1; i ++){
            profits[i] = prices[i + 1] - prices[i];
        }
        int maxProfits = 0;
        for(int i = 0; i < prices.length - 1; i ++){
            if(profits[i] > 0) maxProfits += profits[i];
        }
        return maxProfits;
    }

    public static void main(String[] args) {
        int[] price = {1,2,3,4,5};
        System.out.println(maxProfit(price));
    }
}
