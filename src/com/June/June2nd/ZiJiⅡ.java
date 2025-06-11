package com.June.June2nd;
//回溯篇
//子集Ⅱ
import java.util.*;


public class ZiJiⅡ {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
        backtracking(result,path,0,nums);
        return new ArrayList<>(result);

}
    public static void backtracking(List<List<Integer>> result,List<Integer> path,int startIndex,int[] nums){
        result.add(new ArrayList<>(path));
        if(startIndex >= nums.length){
            return;
        }
        for(int i = startIndex; i < nums.length; i ++){
            //这条去重是很重要的
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtracking(result,path,i + 1,nums);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,4,4,1,4};
        System.out.println(subsetsWithDup(nums));
    }
}
