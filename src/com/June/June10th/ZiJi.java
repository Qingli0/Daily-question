package com.June.June10th;
//回溯篇
// 子集

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums，数组中的元素互不相同，返回该数组所有可能的子集
 */
public class ZiJi {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(result,path,0,nums);
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int startIndex,int[] nums){
        result.add(new ArrayList<>(path));
        if(startIndex >= nums.length){
            return;
        }
        for(int i = startIndex; i < nums.length; i ++){
            path.add(nums[i]);
            backtracking(result,path,i + 1,nums);
            path.remove(path.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }
}
