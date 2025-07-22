package com.July.July26th;
//动态规划
//最长递增子序列

import java.util.Arrays;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 *
 * 子序列 是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的
 * 。
 */
public class ZuiChangDiZengZiXuLie {
    public static int lengthOfLIS(int[] nums) {
        //dp[i]表示到下标为i最长的递增子序列
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int res = 1;
        for(int i = 1; i < nums.length; i ++){
            for(int j = 0; j < i; j ++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[j] + 1,dp[i]);
                }
                res = Math.max(dp[i],res);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums = {};
        System.out.println(lengthOfLIS(nums));
    }
}
