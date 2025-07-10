package com.July.July12th;
//动态规划篇
//完全背包

import java.util.Scanner;

/**
 *
 */
public class WanQuanBeiBao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //n v 分别代表物品的个数和背包的体积
        int n = scanner.nextInt();
        int v = scanner.nextInt();
        //weight 数组是物品的重量数组，value是物品的价值数组
        int[] weight = new int[n];
        int[] value = new int[n];
        //dp2 是一维的滚动数组
        int[] dp2 = new int[v + 1];
        int[][] dp = new int[n][v + 1];
        for(int i = 0; i < n; i ++){
            weight[i] = scanner.nextInt();
            value[i] = scanner.nextInt();
        }
        // weight[0] 是第一个物品的重量
        for(int i = 0; i <= v; i ++){
            if(i % weight[0] == 0){
                int beishu = i / weight[0];
                dp[0][i] = beishu * value[0];
            }
        }
        for(int i = 0; i < n; i ++){
            for(int j = weight[i]; j <= v; j ++){
                dp2[j] = Math.max(dp2[j],dp2[j - weight[i]] + value[i]);
            }
        }
        System.out.println(dp2[v]);
        for(int i = 1; i < n; i ++){
            for(int j = 1; j <= v; j ++){
                if(weight[i] <= j){
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - weight[i]] + value[i]);
                }else dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[n - 1][v]);

    }
}
