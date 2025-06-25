package com.June.June16th;
//贪心篇
//柠檬水找零

/**
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元，顾客排队购买你的产品(按账单bills 支付的顺序) 一次购买一杯
 * 每位顾客只买一杯柠檬水，然后向你支付 5 美元、10美元 或20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客想你支付5 美元
 * 这道题真的很简单
 */
public class NingMengShuiZhaoLing {
    public static boolean lemonadeChange(int[] bills){
        //我的思路是，分别要记录现在手中的 5 元，10 元纸币的数量，初始化为 0
        int tenyuancount = 0,fiveyuancount = 0;
        for(int i = 0; i < bills.length; i ++){
            if(bills[i] == 5) {
                fiveyuancount++;
                continue;
            }
            if(bills[i] == 10 && fiveyuancount < 1) return false;
            else if(bills[i] == 10 && fiveyuancount >= 1){
                fiveyuancount --;
                tenyuancount ++;
                continue;
            }
            // 如果当前的纸币是20 要的是 1张10元和一张5元(这个优先级高) 或者 3 张 5元
            if(bills[i] == 20 && tenyuancount >= 1 && fiveyuancount >= 1){
                tenyuancount --;
                fiveyuancount --;
            }else if(bills[i] == 20 && tenyuancount >= 1){
                //说明 5元不够
                return false;
            }else if(bills[i] == 20 && fiveyuancount >= 3){
                fiveyuancount -= 3;
            }else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] bills = {5,5,10,10,20};
        System.out.println(lemonadeChange(bills));
    }
}
