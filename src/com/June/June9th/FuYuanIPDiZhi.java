package com.June.June9th;
//回溯章
//回溯中的切割问题，和 6 月 3 号切割回文串那题一样
//复原IP地址

import java.util.ArrayList;
import java.util.List;

/**
 *有效IP地址 正好由四个整数(每个整数位于0 到 255之间构成)
 * 切割问题 startIndex是一定需要的，因为不能重复切割，需要记录下一切割点的位置
 * 这里分割之后需要添加 . 而且分成四个部分 终止条件就不是 startIndex >= s.length()
 * 终止条件就是 pointNum ==3
 */
public class FuYuanIPDiZhi {
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder builder = new StringBuilder(s);
        backtracking(result,builder,0,0);
        return result;
    }
    public static void backtracking(List<String> result,StringBuilder s,int startIndex,int pointNum){
        if(pointNum == 3){
            //已经是第四段，判断第四段是否是合法的
            if(isValid(s,startIndex,s.length() - 1)){
                result.add(s.toString());
                return;
            }
        }
        for(int i = startIndex; i < s.length(); i ++){
            if(isValid(s,startIndex,i)){
                // 从 startIndex 到 i 这中间的都是合法的，插入一个 . 并且往下递归
                s.insert(i + 1,".");
                pointNum ++;
                backtracking(result,s,i + 2,pointNum);
                //回溯
                pointNum --;
                s.deleteCharAt(i + 1);
            }else break;
        }
    }
    public static boolean isValid(StringBuilder s,int start ,int end){
        if(end < start) return false;
        if(s.charAt(start) == '0' && end != start) return false; // 012 这样的情况
        int num = 0;
        for(int i = start;i <= end;i ++){
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;
            if(num > 255) return false;
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("101023"));
    }
}
