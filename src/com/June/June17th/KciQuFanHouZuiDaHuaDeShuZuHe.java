package com.June.June17th;
//贪心篇
//K次取反后最大化的数组和

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 给你一个整数数组nums和一个整数k，按一下方法修改该数组：
 *   选择某个下标i 并将nums[i] 替换成 - nums[i]
 *   重复这个过程恰好 k 次，可以多次选择同一个下标 i。
 *   以这种方式修改数组后，返回数组可能的 最大和。
 *   -------------------------------------------------
 *   我的思路是：先将整个数组进行排序，贪心那肯定是所有的负数变成正数是最好的。
 *   如果排完序之后第一个数就是正数，那就反复在第一个数进行 反转
 *   如果排完序之后 前面有 n 个负数并 n < k 那么将这n个负数全部反转，变成全部都是正数，然后将整个数组再次排序，对第一个元素进行反复反转
 *   如果 前面有 n 个负数，n > k 那就是前k个数反转
 */
public class KciQuFanHouZuiDaHuaDeShuZuHe {
    public static int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;
        //这是第一种情况
        if(nums[0] >= 0){
            for(int i = 0; i < k; i ++){
                nums[0] = - nums[0];
            }
            for (int num : nums) {
                res += num;
            }
            return res;
        }
        //第二种情况，统计负数的值
        int Count_fushu = 0;
        for (int j : nums) {
            if (j < 0) Count_fushu++;
        }
        if(Count_fushu <= k){
            for(int i = 0; i < Count_fushu; i ++){
                nums[i] = -nums[i];
            }
            Arrays.sort(nums);
            int count = 0;
            while (count < k - Count_fushu){
                nums[0] = -nums[0];
                count ++;
            }
            for (int num : nums) {
                res += num;
            }
            return res;
        }else{
            for(int i = 0; i < k; i ++){
                nums[i] = -nums[i];
            }
            for (int num : nums) {
                res += num;
            }
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,-3,-1,5,-4};
        int k = 2;
        System.out.println(largestSumAfterKNegations(nums, k));
    }
}
