package com.June.June21th;
//贪心章
//摆动序列

/**
 * 如果连续数字之间的差严格在正数和负数之间交替，则数字序列称为摆动序列
 * 第一个差(如果存在的话)可能时正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列
 */
public class BaiDongXuLie {
    public static int wiggleMaxLength(int[] nums) {
        int result = 1;
        int prediff = 0;
        for(int i = 0; i < nums.length - 1; i ++){
            //遍历到这里的时候，说明nums.length至少为 3
            int postdiff = nums[i + 1] - nums[i];
            if(prediff <= 0 && postdiff > 0 || prediff >= 0 && postdiff < 0){
                result ++;
                prediff = postdiff;
            }

        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        System.out.println(wiggleMaxLength(nums));
    }
}
