package com.June.June15th;
//贪心章
//单调递增的数字

/**
 * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。
 *
 * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
 */

/**
 * 这道题的解题思路是其实就是 每次比较到str[i - 1] > str[i] 的时候，str[i -1] --,str[i] = 9
 */
public class DanDiaoDiZengDeShuZi {
    public static int monotoneIncreasingDigits(int n) {
        String str = String.valueOf(n);
        char[] s = str.toCharArray();
        int flag = s.length;
        for(int i = s.length - 1; i > 0; i --){
            if(s[i - 1] - '0' > s[i] - '0'){
                flag = i;
                s[i - 1] = (char)(s[i - 1] - 1);
            }
        }
        for(int i = flag; i < s.length; i ++){
            s[i] = '9';
        }
        return Integer.parseInt(String.valueOf(s));
    }

    public static void main(String[] args) {
        int n = 100;
        System.out.println(monotoneIncreasingDigits(n));
    }
}
