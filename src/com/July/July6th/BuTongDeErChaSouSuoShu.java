package com.July.July6th;
//动态规划
//不同的二叉搜索树

/**
 * 给你一个整数n,求恰好n个节点组成且节点值从1 到 n 互不相同的二叉搜索树有多少种?
 * 返回符合题意得二叉树种数
 */
public class BuTongDeErChaSouSuoShu {
    public static int numTrees(int n) {
        /*首先初始化前面比较简单的dp dp[0] = 1,dp[1] = 1,dp[2] = 2
        对于每一棵二叉树，就拿三来说，一定要有一个根节点，剩下的节点一定是在根节点的左右两侧
        1 根 1, 0 根 2, 2 根 0 这三种情况
        dp[3] = dp[1] * dp[1] + dp[0] * dp[2] + dp[2] * dp[0]
         */
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i ++){
            for(int j = 1; j <= i; j ++){
                dp[i] += dp[i - j] * dp[j - 1];
            }
        }
        for (int i = 0; i <= n; i ++){
            System.out.print(dp[i] + " ");
        }
        return dp[n];

    }

    public static void main(String[] args) {
        System.out.println(numTrees(6));
    }
}
