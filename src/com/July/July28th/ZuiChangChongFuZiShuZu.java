package com.July.July28th;
//动态规划
//最长重复子数组

/**
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 */
public class ZuiChangChongFuZiShuZu {
    public static int findLength(int[] nums1, int[] nums2) {
        int dp[][] = new int[nums1.length + 1][nums2.length + 1];
        int res = 0;
        for(int i = 1; i <= nums1.length; i ++){
            for(int j = 1; j <= nums2.length; j ++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(dp[i][j],res);
            }
        }
        for(int i = 0; i <= nums1.length; i ++){
            for(int j = 0; j <= nums2.length; j ++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        return res;
    }
    public static void main(String[] args) {
        int[] nums1 = {0,0,0,0,0,0};
        int[] nums2 = {0,0,0,0,0,0,0,0};
        System.out.println(findLength(nums1, nums2));
    }
}
