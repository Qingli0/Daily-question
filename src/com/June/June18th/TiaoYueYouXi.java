package com.June.June18th;
//贪心章
//跳跃游戏

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给你一个非负整数数组nums，你最初位于数组的 第一个 下标。数组中的每个元素代表你在该位置可以跳跃的最大长度
 * 判断你在该位置可以跳跃的最大长度
 * 判断你是否能够到达最后一个下标，如果可以，返回true，否则返回false
 */
public class TiaoYueYouXi {
    public static boolean canJump(int[] nums) {
        if (nums.length == 1) return true;
        int coverRange = 0;
        //coverRange 是每一次迭代可以覆盖的最远距离，
        //在所有覆盖的最远距离的范围里面，一步一步走，遇到更远的就更新覆盖的范围，直到覆盖的范围超过了最终的距离。
        for (int i = 0; i <= coverRange;i ++) {
            coverRange = Math.max(nums[i] + i,coverRange);
            if(coverRange >= nums.length - 1) return true;
        }
        return false;
    }
    public static void main(String[] args) {
        int[] nums = {4,2,0,0,1,1,4,4,4,0,4};
        System.out.println(canJump(nums));
    }
}
