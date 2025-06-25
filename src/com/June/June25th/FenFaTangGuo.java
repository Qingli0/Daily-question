package com.June.June25th;
//贪心篇
//分发糖果

/**
 * n 个孩子站在一排，给你一个正数数组，ratings 表示每个孩子的评分。
 * 你需按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果
 * 相邻的两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的最少糖果数目：
 */
public class FenFaTangGuo {
    public static int candy(int[] ratings) {
        int res = 0;
        int[] candy1 = new int[ratings.length];
        candy1[0] = 1;
        int[] candy2 = new int[ratings.length];
        candy2[ratings.length - 1] = 1;
        for(int i = 1; i < ratings.length; i ++){
            //第一种情况 右边比左边大
            if(ratings[i] > ratings[i - 1]) {
                candy1[i] = candy1[i - 1] + 1;
            }else
                candy1[i] = 1;
        }
        for(int i = ratings.length - 2; i >= 0; i --){
            //第二种情况 左边比右边大
            if(ratings[i] > ratings[i + 1]){
                candy2[i] = Math.max(candy1[i],candy2[i + 1] + 1);
            }
            else candy2[i] = Math.max(candy1[i], 1);
        }
        candy2[0] = Math.max(candy1[0],candy2[0]);
        candy2[ratings.length - 1] = Math.max(candy1[ratings.length -1],candy2[ratings.length - 1]);
        for(int i = 0; i < ratings.length ; i ++){
            res += candy2[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ratings = {1,2,3};
        System.out.println(candy(ratings));
    }
}
