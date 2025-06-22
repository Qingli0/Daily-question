package com.June.June22nd;
// 贪心章
//最大子数组和
public class ZuiDaZiShuZuHe {
    public static int maxSubArray(int nums[]){
        //我自己先试试动态规划吧
        //定于一个数组dp[nums.length] dp[i] 是从nums[0]到nums[i] 的最大子数组和，最终返回max
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for(int i = 1; i < nums.length; i ++){
          if(dp[i - 1] <= 0) dp[i] = nums[i];
          else dp[i] = dp[i - 1] + nums[i];
            if(dp[i] > max) max = dp[i];
        }
        return max;
    }
    public static int tanxinsuanfa(int nums[]){
        int result = Integer.MIN_VALUE;
        int count = 0;
        //贪心算法的思路就是，不断的累加，当累加的count < 0 那么从nums[i + 1] 开始重新累加
        //而最大的count 已经记录在了result中也就是说result 记录的是整个遍历过程中最大的子序列的和
        for(int i = 0; i < nums.length; i ++){
            count += nums[i];
            if(count > result) result = count;
            if(count < 0) count = 0;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,-1,-1,-1,-1};
        System.out.println(tanxinsuanfa(nums));
    }
}
