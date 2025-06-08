package com.June.June8th;
//回溯章
//组合总数Ⅱ

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个候选人编号的集合candidates 和一个目标数target，找出candidates中所有可以使数字和为target的组合
 * 这个跟组合总数的差别在于每个数字只能用一次
 */
public class ZuHeZongShuⅡ {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtracking(result,path,candidates,target,0,0);
        return result;
    }
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int[] candidates,int target,int sum, int startindex){
        if(sum > target) return;
        if(sum == target) {
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = startindex; i < candidates.length; i ++){
            //这条逻辑是去重，去重的前提是要对元素进行排序
            if (i > startindex && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            sum += candidates[i];
            backtracking(result,path,candidates,target,sum,i + 1);
            sum -= candidates[i];
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        System.out.println(combinationSum2(candidates, 8));
    }
}
