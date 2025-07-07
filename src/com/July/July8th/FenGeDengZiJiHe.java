package com.July.July8th;
//动态规划篇
//分割等子集和

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class FenGeDengZiJiHe {
    public static boolean canPartition(int[] nums) {
        /**
         * 这道题翻译一下就是0 1背包问题，v[] 和 w[] 都是 nums[]数组，背包的容量是 sum/2 当背包装满，里面的价值是否是sum / 2即dp[num.length - 1][sum / 2]
         * 如果是的话就是返回true，否则返回false
         */
        int sum = 0;
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
        }
        if(nums.length == 1 || sum % 2 != 0)
        return false;
        int[][] dp = new int[nums.length][sum / 2 + 1];
        for(int i = 0; i < sum / 2 + 1; i ++){
            if(i >= nums[0]) dp[0][i] = nums[0];
        }
        for(int i = 1; i < nums.length; i ++){
            for(int j = 1; j <= sum / 2; j ++){
                if(nums[i] <= j){
                    dp[i][j] = Math.max(dp[i - 1][j],dp[i - 1][j - nums[i]] + nums[i]);
                }
                else dp[i][j] = dp[i - 1][j];
            }
        }
        if(dp[nums.length - 1][sum / 2] == sum / 2) return true;
        else  return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1};
        System.out.println(canPartition(nums));
    }
}
