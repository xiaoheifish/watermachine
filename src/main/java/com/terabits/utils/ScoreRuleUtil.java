package com.terabits.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24.
 */
public class ScoreRuleUtil {

    private static String toFullBinaryString(long num) {
        final int size=42;
        char[] chs = new char[size];
        for(int i = 0; i < size; i++) {
            chs[size - 1 - i] = (char)(((num >> i) & 1) + '0');
        }
        return new String(chs);
    }

    //根据指定的天数移动签到历史
    public static Long moveByte(long oldHistory,long moveAmonut)
    {
        long moveResult= oldHistory<<moveAmonut;
        long result=  Long.parseLong(toFullBinaryString(moveResult),2)+1;
        return result;
    }

    //根据签到历史统计每段连续签到的长度
    private static List<Integer> computeMedal(long signHistory){
        List<Integer> continueSign = new ArrayList<Integer>();
        while(signHistory> 0){
            int count = 0;
            while (signHistory % 2 == 1){
                count++;
                signHistory = signHistory >> 1;
            }
            if(count == 0){
                signHistory = signHistory >> 1;
            }
            if (count != 0) {
                continueSign.add(count);
            }
        }
        return  continueSign;
    }

    //根据连续签到天数计算可获得多少枚金章，银章，铜章
    private static int[] medalNumber(int k){
        int[] medals = new int[3];
        int gold, silver, bronze = 0;
        gold = k / 7;
        k = k - gold * 7;
        silver = k / 5;
        k = k - silver * 5;
        bronze = k / 3;
        medals[0] = gold;
        medals[1] = silver;
        medals[2] = bronze;
        return medals;
    }

    //给定从数据库中取出的签到历史，返回对应的金章，铜章，银章数目
    public static int[] historyToMedal(long signHistory){
        List<Integer> sign = computeMedal(signHistory);
        int[] medals = new int[3];
        int[] tempMedal;
        for(int i = 0; i < sign.size(); i++){
            tempMedal = medalNumber(sign.get(i));
            for(int j = 0; j < 3; j++){
                medals[j] += tempMedal[j];
            }
        }
        return medals;
    }

   /* public static void main(String[] args){
        int[] medal = historyToMedal(2097143);
        for(int i=0;i<3;i++){
            System.out.println(medal[i]);
        }
    }*/


}
