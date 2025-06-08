package com.June.June7th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//回溯章
//组合总数
// 给你一个无重复的整数数组，和目标整数，找到一组candidates中可以使数字和为目标target的所有不同组合。

/**
 * 这道题没有说要去重，也就是candidates中的元素是可以重复使用的，所以，不需要startindex
 */
public class ZuHeZongHe {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(result,path,candidates,target,0,0);
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int[] candidates,int target,int sum,int inx){
        if(target < sum) return;
        if(sum == target){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = inx;i < candidates.length; i ++){
            path.add(candidates[i]);
            sum += candidates[i];
            backtracking(result,path,candidates,target,sum, i);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        System.out.println(combinationSum(candidates, 7));
    }
}
