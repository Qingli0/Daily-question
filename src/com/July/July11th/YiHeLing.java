package com.July.July11th;
//动态规划篇
//一和零

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 *
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 *
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 */
public class YiHeLing {
    public  static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        //dp[i][j] 表示i个0 j个1最大装的物品,最后返回dp[i][j]
        for (String str : strs) {
            int x = 0,y = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == '0') x ++;
                else y ++;
            }
            //别看这里是两个for循环，只是这里是两个维度的容量，其实是滚动数组，如果不是滚动数组应该是是dp[][][]这样才对。
            //所以这里只能逆序，防止物品重复利用。
            for(int i = m; i >= x; i --){
                for(int j = n; j >= y; j --){
                    dp[i][j] = Math.max(dp[i][j],dp[i - x][j - y] + 1);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String[] strs = {"10", "0", "1"};
        int m = 1,n = 1;
        System.out.println(findMaxForm(strs, m, n));
    }
}
