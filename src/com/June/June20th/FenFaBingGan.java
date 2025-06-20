package com.June.June20th;
//贪心篇
//分发饼干问题

import java.util.Arrays;

/**
 * 假设你是一个家长，想要给你的孩子们分一些小饼干，但是每个孩子最多能给一块饼干。
 */
public class FenFaBingGan {
    public static int findContentChildren(int[] g, int[] s) {
        //最简单的思路应该是将两个数组都从小到大排序，然后依次去匹配
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        int result = 0;
        while (i < g.length && j < s.length){
            if(g[i] <= s[j]){
                result ++;
                i ++;
                j ++;
            }else{
                //g[i] > s[j] 的情况
                j ++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(findContentChildren(g, s));
    }
}
