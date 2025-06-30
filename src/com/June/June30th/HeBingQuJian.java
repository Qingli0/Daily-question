package com.June.June30th;
//贪心篇
//合并区间

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class HeBingQuJian {
    public static int[][] merge(int[][] intervals) {
        //根据左区间的大小，从小到大进行排序
        Arrays.sort(intervals,(a,b) -> Integer.compare(a[0],b[0]));
        List<int[]> list = new ArrayList<>();
        int count = 1;
        for(int i = 1; i < intervals.length; i ++){
            if(intervals[i - 1][1] < intervals[i][0]){
                //这个时候说明区间是不重合的，需要添加一个结果
                count ++;
                list.add(intervals[i - 1]);
            }else {
                //说明是有重合的,更新整个大区间
                intervals[i][0] = Integer.min(intervals[i - 1][0],intervals[i][0]);
                intervals[i][1] = Integer.max(intervals[i - 1][1],intervals[i][1]);
            }
        }
        list.add(intervals[intervals.length - 1]);
        System.out.println(count);
        return list.toArray(new int[list.size()][2]);
    }

    public static void main(String[] args) {
        int[][] res = {{1,4},{4,5},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(merge(res)));
    }
}
