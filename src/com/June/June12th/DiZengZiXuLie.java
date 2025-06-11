package com.June.June12th;

import java.util.ArrayList;
import java.util.List;

//回溯章
//递增子序列 - 非递减子序列

/**
 * 给你一个整数数组nums，找出并返回所有该数组中不同的递增子序列，递增子序列中至少有两个元素，
 */
public class DiZengZiXuLie {
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(result,path,nums,0);
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int[] nums,int startIndex){
        if(path.size() >= 2){
            result.add(new ArrayList<>(path));
//            return;
        }
        int[] used = new int[201];
        for(int i = startIndex;i < nums.length; i ++){
            if (!path.isEmpty() && nums[i] < path.get(path.size() - 1) || used[nums[i] + 100] == 1) {
                continue;
            }
            used[nums[i] + 100] = 1;
            path.add(nums[i]);
            backtracking(result,path,nums,i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,4,3,2,1};
        System.out.println(findSubsequences(nums));
    }
}
