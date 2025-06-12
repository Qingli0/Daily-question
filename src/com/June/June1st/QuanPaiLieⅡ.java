package com.June.June1st;
//回溯章
//全排列

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 给定一个可包含重复数字的序列nums，按任意顺序返回所有不重复的全排列
 * 方案一： 比较低级，就是用一个set集合去接收，然后一个个去去重，相当于是没有进行优化
 *
 * 方案二： 效率比较高，就是要去重。
 * if(i > 0 && nums[i - 1] == nums[i] && start[i - 1] == 0){
 *                 continue;
 *             }
 * 层次上去重，当在同一层时，如果当前的 i 位置上面的nums[i] = nums[i-1] 并且 start[i - 1] = 0
 * 说明我这个用了反而前面那个没用用到，这个就要去重
 */
public class QuanPaiLieⅡ {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
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
            if(i > 0 && nums[i - 1] == nums[i] && start[i - 1] == 0){
                continue;
            }
             /* 层次上去重，当在同一层时，如果当前的 i 位置上面的nums[i] = nums[i-1] 并且 start[i - 1] = 0
                    * 说明我这个用了反而前面那个没用用到，这个就要去重
                    */
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
        int[] nums = {1,1,2};
        System.out.println(permuteUnique(nums));
    }
}
