package com.July.July15th;
//动态规划
//爬楼梯进阶版

import java.util.Scanner;

/*
假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

每次你可以爬至多m (1 <= m < n)个台阶。你有多少种不同的方法可以爬到楼顶呢？

注意：给定 n 是一个正整数。
 */
public class PaLouTiJinJieBan {
    /**
     * 可以将这道题看成是一个完全背包，背包的容量就是n，1 ~ m是每个物品的容量，
     * 问装满这个背包有多少种装法，其实就是完全背包的排列问题。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // n 是最终爬楼的终点
        int n = scanner.nextInt();
        // m 是每一次最多爬楼的数量
        int m = scanner.nextInt();

        int dp[] = new int[n + 1];
        dp[0] = 1;
        // 完全背包的排列问题要先遍历背包的容量，再遍历背包的物品
        //dp[j] += dp[j - i]
        for(int j = 1; j <= n; j ++){
            for(int i = 1; i <= m; i ++){
                if(j >= i){
                    dp[j] += dp[j - i];
                }
            }
        }
        System.out.println(dp[n]);
    }
}
