package com.July.July18th;
//动态规划篇
//单词拆分

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DanCiChaiFen {
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        //dp[j] = true 表示 前面 dp[j - 1] 个字符在字典里面
        dp[0] = true;
        //先遍历背包
        for(int j = 1; j <= s.length(); j ++){
            //再遍历物品
            for(int i = 0; i < j; i ++){
                String substring = s.substring(i,j);
                if(wordDict.contains(substring) && dp[i]){
                    dp[j] = true;
                }
            }
        }
        for(int i = 0; i < dp.length; i ++){
            System.out.print(dp[i] + " ");
        }
        System.out.println();
        return false;
    }
    public static void main(String[] args) {
        String s = "applepenapple";
        List<String> wordDict = new ArrayList<>(Arrays.asList("apple", "pen"));
        System.out.println(wordBreak(s, wordDict));
    }
}
