package com.June.June11th;

import java.util.ArrayList;
import java.util.List;

//回溯章
//全排列

/**
 * 给定一个不含重复数字的数组mums，返回其所有可能的全排列你可以按照任意顺序返回答案
 */
public class QuanPaiLie {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int[] start = new int[nums.length];
        //start 表示这个数是否被用过了，用过了就设置为1
        backtracking(result,path,nums,start);
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int[] nums,int[] start){
        if(path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }

        for(int i = 0; i < nums.length; i ++){
            if(start[i] == 0){
                path.add(nums[i]);
                start[i] = 1;// 说明这个被用过了
                backtracking(result,path,nums,start);
                start[i] = 0;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }
}
