package com.June.June26th;
//贪心章
//根据身高重建队列

import java.util.Arrays;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组people表示队列中一些人的属性(不一定按照顺序),每个人people[i] = [hi,ki]表示第i 个人的身高为hi，前面正好有
 * ki个人身高大于或者等于hi的人 。
 */
public class GenJvShenGaoChongJianDuiLie {
    public static int[][] reconstructQueue(int[][] people) {
        for(int i = 0; i < people.length - 1; i ++){
            for(int j = 0; j < people.length - i - 1; j ++){
                if(people[j][0] < people[j + 1][0]){
                    int[] temp = people[j];
                    people[j] = people[j + 1];
                    people[j + 1] = temp;
                }
                if(people[j][0] == people[j + 1][0]){
                    if(people[j][1] > people[j + 1][1]){
                        int[] temp = people[j];
                        people[j] = people[j + 1];
                        people[j + 1] = temp;
                    }
                }
            }
        }
        for(int i = 0; i < people.length; i ++){
            if(people[i][1 ] == i) continue;
            else {
                int[] temp = people[i];
                for(int k = i; k > temp[1]; k --){
                    people[k] = people[k - 1];
                }
                people[temp[1]] = temp;
            }
        }
        return people;
    }

    public static void main(String[] args) {
        int[][] people = {{6,0},{5,0},{4,0},{3,2},{2,2},{1,4}};
        System.out.println(Arrays.deepToString(reconstructQueue(people)));
    }
}
