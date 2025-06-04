package com.June.June4th;

import java.util.ArrayList;
import java.util.List;

//回溯章
//组合
public class ZuHe {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtracking(n,k,path,result,1);
        return result;
    }
    public void backtracking(int n,int k,List<Integer> path,List<List<Integer>> result,int startIndex){
        if(path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }
        for(int i = startIndex; i <= n; i ++){
            path.add(i);
            //递归
            backtracking(n,k,path,result,i + 1);
            //回溯
            path.remove(path.size() - 1);
        }
    }
    //回溯递归可以优化对其进行剪枝
//    for(int i = startIndex; i <= n - (k - path.size()) + 1; i ++){
//        path.add(i);
//        //递归
//        backtracking(n,k,path,result,i + 1);
//        //回溯
//        path.remove(path.size() - 1);
//    }
}
