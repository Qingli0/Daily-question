package com.July.July10th;
//动态规划篇
//目标合并

/**
 * 给你一个非负整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 *     例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 *
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */
public class MuBiaoHe {
    public static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
        }
        if((sum + target) % 2 != 0 || Math.abs(target) > sum) return 0;

        //问题转化为用nums[]这个物品集合 装满bagSize容量的包有多少种装法
        int bagSize = (sum + target) / 2;
        int[][] dp = new int[nums.length][bagSize + 1];
        int numZeros = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                numZeros++;
            }
            dp[i][0] = (int) Math.pow(2, numZeros);

        }
        for(int i = 1; i <= bagSize; i ++){
            if(nums[0] == i) dp[0][i] = 1;
        }
        for(int i = 1; i < nums.length; i ++){
            for(int j = 1; j <= bagSize; j ++){
                if(nums[i] > j) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i -1][j] + dp[i - 1][j - nums[i]];
            }
        }
        return dp[nums.length - 1][bagSize];
    }

    public static void main(String[] args) {
        int target = 3;
        int[] nums = {1,1,1,1,1};
        System.out.println(findTargetSumWays(nums, target));
    }
}
