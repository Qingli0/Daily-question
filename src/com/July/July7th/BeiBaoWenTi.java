package com.July.July7th;
//动态规划篇
//0/1背包问题


import java.util.Scanner;

/**
 *  小明是一位科学家，他需要参加一场重要的国际科学大会，以展示自己的最新研究成果。他需要带一些研究材料，但是他的行李箱空间有限。
 *  这些研究材料包括实验设备、文献资料和实验样本等等，它们各自占据不同的空间，并且具有不同的价值。
 *
 * 小明的行李空间为 N，问小明应该如何抉择，才能携带最大价值的研究材料，每种研究材料只能选择一次，并且只有选与不选两种选择，不能进行切割
 */
public class BeiBaoWenTi {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int bagNum = scanner.nextInt();
        int spaceAll = scanner.nextInt();
        int[] space = new int[bagNum];
        int[] value = new int[bagNum];
        for(int i = 0; i < bagNum; i ++){
            space[i] = scanner.nextInt();
        }
        for(int i = 0; i < bagNum; i ++){
            value[i] = scanner.nextInt();
        }
        int[][] dp = new int[bagNum][spaceAll + 1];
        dp[0][0] = 0;
        for(int i = 1; i <= spaceAll; i ++){
            if(space[0] <= i) dp[0][i] = value[0];
        }
        for(int i = 1; i < bagNum; i ++){
            for(int j = 1; j <= spaceAll; j ++){
                if(space[i] <= j)
                dp[i][j] =  Math.max(dp[i - 1][j],dp[i - 1][j - space[i]] + value[i]);
                else dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[bagNum - 1][spaceAll]);
    }
}
