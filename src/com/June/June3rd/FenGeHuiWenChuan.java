package com.June.June3rd;
//回溯章
//分割回文串

import javax.xml.xpath.XPath;
import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个字符串s，请你将s分割成一些字串，使每个字串都是回文
 * 解题思路：这里的分割和 组合 是一样的
 * a b c 组合是 第一个取了a 第二个在bc里面取
 * a b c 分割时 第一个分割 a bc 第二个 要么是 a b c 要么是 a bc两种分割方式。
 * 一样的分割不能重复，所以需要startIndex
 */
public class  FenGeHuiWenChuan {
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        backtracking(result,path,0,s);
        return result;

    }
    public static void backtracking(List<List<String>> result,List<String> path,int startIndex,String s){
        if(startIndex >= s.length()){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = startIndex;i < s.length(); i ++){
            if(isHuiWen(startIndex,i,s)){
                String string = s.substring(startIndex, i + 1);
                path.add(string);
            }else continue;
            backtracking(result,path,i + 1,s);
            path.remove(path.size() - 1);
        }

    }
    public static boolean isHuiWen(int start,int end,String s){
        boolean flag = true;
        for(int i = start,j = end; i < end; i ++,j --){
            if(s.charAt(i) == s.charAt(j)) continue;
            else {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(isHuiWen(0,2,"qwq"));
        System.out.println(partition("a"));
    }
}
