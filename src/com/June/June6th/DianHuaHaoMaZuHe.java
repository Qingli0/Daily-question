package com.June.June6th;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//回溯章
//电话号码的组合
public class DianHuaHaoMaZuHe {
    private static final String[] numString = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        int length = digits.length();
        if(length == 0) return result;
        //digits的长度是递归树的深度，也就是path的length
        String path = "";
        backtracking(path,length,result,0,digits);
        return result;
    }
    public static void backtracking(String path,int length,List<String> result,int index,String digits){
        if(path.length() == length){
            result.add(path);
            return;
        }
        int digit = Character.getNumericValue(digits.charAt(index));
        String letter = numString[digit];
        for(int i = 0; i < letter.length(); i ++){
            path += letter.charAt(i);
            backtracking(path,length,result,index + 1,digits);
            path = path.substring(0, path.length() - 1);
        }

    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
