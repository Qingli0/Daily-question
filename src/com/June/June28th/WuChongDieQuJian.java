package com.June.June28th;
//贪心篇
//无重叠区间

import java.util.Arrays;

/**
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 * 注意 只在一点上接触的区间是 不重叠的。例如 [1, 2] 和 [2, 3] 是不重叠的。
 */
public class WuChongDieQuJian {
    public static int eraseOverlapIntervals(int[][] intervals) {
        //对区间按照从小大排序
        Arrays.sort(intervals,(a,b) -> Integer.compare(a[0],b[0]));
        int count = 1;
        for (int i = 1; i < intervals.length; i ++){
            if(intervals[i - 1][1] <= intervals[i][0]){
                count ++;
            }else {
                intervals[i][1] = Math.min(intervals[i][1],intervals[i - 1][1]);
            }
        }
        return intervals.length - count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2},{1,2},{1,2}};
        System.out.println(eraseOverlapIntervals(intervals));
    }
}

