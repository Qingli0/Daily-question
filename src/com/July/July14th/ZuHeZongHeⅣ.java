package com.July.July14th;
//动态规划篇
//组合总和Ⅳ
public class ZuHeZongHeⅣ {
    public static int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int j = 1; j <= target; j ++){
            for(int i = 0; i < nums.length; i ++){
                if(j >= nums[i])
                    dp[j] += dp[j - nums[i]];
            }
        }
        return dp[target];
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int target = 4;
        System.out.println(combinationSum4(nums, target));

    }
}
