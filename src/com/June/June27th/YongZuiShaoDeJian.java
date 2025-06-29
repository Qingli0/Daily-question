package com.June.June27th;
//贪心章
//用最少数量的箭引爆气球

/**
 * 有一些球形气球贴在一堵 XY 平面表示的墙面上。墙面上的气球记录在整数数组points，其中points[i] = [xstart,xend]表示水平直径在 xstart 和 xend之间的气球
 * 你不知道啊气球的确切 y 坐标
 * 一只弓箭可以沿着 x轴从不同点完全垂直射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。
 * 可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 */

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 说白了那么多，就是区间重合
 */
public class YongZuiShaoDeJian {
    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a,b) -> Integer.compare(a[0],b[0]));
        int count = 1;
        for(int i = 1; i < points.length; i ++){
            if(points[i - 1][1] < points[i][0]){
                // 区间没有重叠，需要的箭 + 1
                count ++;
            }else {
                //说明是重叠的区间，更新最右端的最小位置
                points[i][1] = Math.min(points[i][1],points[i - 1][1]);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] points = {{10,16},{2,8},{1,6},{7,12}};
        System.out.println(findMinArrowShots(points));
    }
}
