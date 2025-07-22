package com.July.July20th;
//动态规划
//打家劫舍Ⅱ

/**
 * 在打家劫舍的基础上加上了首位相连形成环的条件。
 */
public class DaJiaJieSheⅡ {
    public static int rob(int[] nums){
        int[] dp = new int[nums.length - 1];
        int[] dp2 = new int[nums.length -1];
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        //第一个数组
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for(int i = 2; i < nums.length - 1; i ++)
            dp[i] = Math.max(dp[i - 1],dp[i - 2] + nums[i]);
        for(int i = 0; i < nums.length - 1; i ++)
            System.out.print(dp[i] + " ");
        System.out.println();
        //第二个数组
        dp2[0] = nums[1];
        dp2[1] = Math.max(nums[1],nums[2]);
        for(int i = 3,j = 2; i < nums.length; i ++,j ++)
            dp2[j] = Math.max(dp2[j - 1],dp2[j - 2] + nums[i]);
        for(int i = 0; i < nums.length - 1; i ++)
            System.out.print(dp2[i] + " ");
        System.out.println();
        return Math.max(dp[nums.length - 2],dp2[nums.length - 2]);
    }
    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(rob(nums));
    }
}
