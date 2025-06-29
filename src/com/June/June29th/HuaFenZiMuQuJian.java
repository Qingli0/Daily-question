package com.June.June29th;
//贪心章
//划分字母区间

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串s，我们要把这个字符串划分成尽可能多的片段，同一字母最多出现在一个片段中，例如：
 * 字符串"ababcc" 能够被划分成["abab","cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 */
public class HuaFenZiMuQuJian {
    public static List<Integer> partitionLabels(String s) {
        List<Integer> result = new ArrayList<>();
//        Map<Character, Integer> map = new HashMap<>();
//        for(int i = 0; i < s.length(); i ++){
//            map.putIfAbsent(s.charAt(i), i);
//            if(map.get(s.charAt(i)) != null){
//                map.put(s.charAt(i),i);
//            }
//        }
        int hash[] = new int[27]; // i为字符，hash[i]为字符出现的最后位置
        for (int i = 0; i < s.length(); i++) { // 统计每一个字符最后出现的位置
            hash[s.charAt(i) - 'a'] = i;
        }
        int max_index = 0;
        int flag = 0;
        for(int i = 0; i < s.length(); i ++){
            int index = hash[s.charAt(i) - 'a'];
            if(index > max_index) max_index = index;
            if(i == max_index){
                    result.add(i + 1 - flag);
                    flag = i + 1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "eccbbbbdec";
        System.out.println(partitionLabels(s));
    }
}
