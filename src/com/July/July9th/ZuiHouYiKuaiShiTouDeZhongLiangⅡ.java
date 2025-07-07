package com.July.July9th;
//动态规划篇
//最后一块石头的重量Ⅱ

/**
 * 有一堆石头，用整数数组 stones 表示。其中 stones[i] 表示第 i 块石头的重量。
 *
 * 每一回合，从中选出任意两块石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 *     如果 x == y，那么两块石头都会被完全粉碎；
 *     如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 *
 * 最后，最多只会剩下一块 石头。返回此石头 最小的可能重量 。如果没有石头剩下，就返回 0。
 */
public class ZuiHouYiKuaiShiTouDeZhongLiangⅡ {
    public static int lastStoneWeightII(int[] stones) {
        /*这道题的核心还是背包问题，两个石头相撞，意思就是想要两个石头的差值最小。
        相当于是：将两堆石头分成差值最小的两堆，相减就是本题的答案。
        value[] 和 weight[]数组都是stones[],求背包容量是 sum / 2时的最大价值是多少。
        * */
        int sum = 0;
        for (int i = 0; i < stones.length; i ++){
            sum += stones[i];
        }
        int halfsum = sum / 2;
        int dp[][] = new int[stones.length][halfsum + 1];
        for(int i = 0; i <= halfsum; i ++){
            if(stones[0] <= i) dp[0][i] = stones[0];
        }
        for(int i = 1; i < stones.length; i ++){
            for(int j = 1; j <= halfsum; j ++){
                if(stones[i] <= j)
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i- 1][j - stones[i]] + stones[i]);
                else dp[i][j] = dp[i - 1][j];
            }
        }
        return sum - dp[stones.length - 1][halfsum] * 2;
    }

    public static void main(String[] args) {
        int[] stones = {31,26,33,21,40};
        System.out.println(lastStoneWeightII(stones));
    }
}
