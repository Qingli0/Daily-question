package com.June.June24th;
//贪心篇
//加油站

/**
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升，
 * 你有一辆油箱容量无限的汽车，从第 i 个加油站开往第 i + 1个加油站需要消耗汽油cost[i] 升
 */
public class JiaYouZhan {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        /**
         * 这题贪心的思路就是从 curSum = gas[i] - cost[i] 进行累加，如果从哪一次开始 curSum < 0 那么开始的就是i + 1;
         */
        int index = 0;
        int curSum = 0;
        int TotalSum = 0;
        for(int i = 0; i < gas.length; i ++){
            curSum += gas[i] - cost[i];
            TotalSum += gas[i] - cost[i];
            if(curSum < 0){
                index = (i + 1) % gas.length;
                curSum = 0;
            }
        }
        if(TotalSum < 0) return -1;
        return index;
    }

    public static void main(String[] args) {
        int[] gas = {1,2,3,4,5};
        int[] cost = {3,4,5,1,2};
        System.out.println(canCompleteCircuit(gas, cost));
    }
}
