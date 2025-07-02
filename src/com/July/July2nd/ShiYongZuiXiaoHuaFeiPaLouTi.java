package com.July.July2nd;
//动态规划篇
//使用最小花费爬楼梯

/**
 * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。
 *
 * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
 *
 * 请你计算并返回达到楼梯顶部的最低花费。
 */
public class ShiYongZuiXiaoHuaFeiPaLouTi {
    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = dp[0] + cost[1];
        if(cost.length == 2) return Math.min(cost[0],cost[1]);
        if(cost.length == 3) return Math.min(cost[0] + cost[2],cost[1]);
        //以下是针对cost[]数组长度大于三的操作
        for(int i = 2; i <= cost.length; i ++){
            if(i == cost.length) dp[i] = Math.min(dp[i - 1],dp[i - 2]);
            else dp[i] = Math.min(dp[i - 1],dp[i - 2]) + cost[i];
        }
        int[] dp2 = new int[cost.length + 1];
        dp2[1] = cost[1];
        dp2[2] = dp2[1] + cost[2];
        //如果从 下标为 1 开始，那么就没有cost[0] 什么事情了
        for(int i = 3; i <= cost.length; i ++){
            if(i == cost.length) dp2[i] = Math.min(dp2[i - 1],dp2[i - 2]);
            else dp2[i] = Math.min(dp2[i - 1],dp2[i - 2]) + cost[i];
        }
        return Math.min(dp[cost.length],dp2[cost.length]);
    }
    public static void main(String[] args) {
        int[] cost = {0,0,0,1};
        System.out.println(minCostClimbingStairs(cost));
    }
}
