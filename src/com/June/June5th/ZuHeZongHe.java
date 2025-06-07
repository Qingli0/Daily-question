package com.June.June5th;

import java.util.ArrayList;
import java.util.List;

//回溯章
//组合总和Ⅲ
public class ZuHeZongHe {
    private static List<List<Integer>> result = new ArrayList<>();
    private static List<Integer> path = new ArrayList<>();
    public static List<List<Integer>> combinationSum3(int k, int n) {
        backtracking(k,n,path,result,0,1);
        return result;
    }
    public static void backtracking(int k,int n,List<Integer> path,List<List<Integer>> result,int sum,int startIndex){
        if(path.size() == k && sum == n){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = startIndex;i <= 9; i ++){
            path.add(i);
            sum += i;
            backtracking(k,n,path,result,sum,i + 1);
            sum -= i;
            path.remove(path.size() - 1);
        }
    }
    public static void main(String[] args) {
        combinationSum3(3,9);
        System.out.println(result);
    }
}
