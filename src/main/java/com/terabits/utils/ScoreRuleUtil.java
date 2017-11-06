package com.terabits.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/10/24.
 */
public class ScoreRuleUtil {

    private static String toFullBinaryString(long num) {
        final int size = 31;
        char[] chs = new char[size];
        for (int i = 0; i < size; i++) {
            chs[size - 1 - i] = (char) (((num >> i) & 1) + '0');
        }
        return new String(chs);
    }


    //根据指定的天数移动签到历史
    public static Long moveByte(long oldHistory, long moveAmonut) {
        long moveResult = oldHistory << moveAmonut;
        long result = Long.parseLong(toFullBinaryString(moveResult), 2) + 1;
        return result;
    }

    //根据签到历史统计每段连续签到的长度
    private static List<Integer> computeMedal(long signHistory) {
        List<Integer> continueSign = new ArrayList<Integer>();
        while (signHistory > 0) {
            int count = 0;
            while (signHistory % 2 == 1) {
                count++;
                signHistory = signHistory >> 1;
            }
            if (count == 0) {
                signHistory = signHistory >> 1;
            }
            if (count != 0) {
                continueSign.add(count);
            }
        }
        return continueSign;
    }


    //将每一个1标记上它属于第几个连续的1，比如0001111000，对应的标记数组就是[0,0,0,4,4,4,4,0,0,0],和下面的函数配合使用，已弃用
   /* private static List<Integer> computeContinueNumber(long signHistory) {
        List<Integer> continueSign = new ArrayList<Integer>(31);
        for (int i = 0; i < 31; i++) {
            continueSign.add(0);
        }
        int left = 0;
        int right = 0;
        String number = toFullBinaryString(signHistory);
        number = number + '0';
        for (int i = 0; i < number.length(); i++) {
            left = i;
            right = i;
            while (number.charAt(i) == '1') {
                right++;
                i++;
            }
            int count = right - left;
            for (int j = left; j < right; j++) {
                continueSign.set(j, count);
            }

        }
        return continueSign;
    }

    private static List<Integer> minusChosenMedal(MedalType[] medal, List<Integer> sign, List<Integer> number) {

        for (int i = 0; i < medal.length; i++) {
            if (medal[i] == MedalType.BRONZE) {
                for (int j = 0; j < sign.size(); j++) {
                    if ((sign.get(j) % 7 == 3) || (sign.get(j) % 7 == 4)) {
                        int tempContinue = sign.get(j);
                        for (int k = 0; k < 3; k++) {
                            number.set(j + k, 0);
                            sign.set(j + k, 0);
                        }
                        for (int k = j + 3; k < j + tempContinue; k++) {
                            sign.set(k, tempContinue - medal[i].getDay());
                        }
                        break;
                    }

                }
            } else if (medal[i] == MedalType.SILVER) {
                for (int j = 0; j < sign.size(); j++) {
                    if ((sign.get(j) % 7 == 5) || (sign.get(j) % 7 == 6)) {
                        int tempContinue = sign.get(j);
                        for (int k = 0; k < 5; k++) {
                            number.set(j + k, 0);
                            sign.set(j + k, 0);
                        }
                        for (int k = j + 5; k < j + tempContinue; k++) {
                            sign.set(k, tempContinue - medal[i].getDay());
                        }
                        break;
                    }

                }
            } else if (medal[i] == MedalType.GOLD) {
                for (int j = 0; j < sign.size(); j++) {
                    if (((sign.get(j) % 7 == 0) && (sign.get(j) != 0)) || (sign.get(j) % 7 == 1) || (sign.get(j) % 7 == 2)) {
                        int tempContinue = sign.get(j);
                        for (int k = 0; k < 7; k++) {
                            number.set(j + k, 0);
                            sign.set(j + k, 0);
                        }
                        for (int k = j + 7; k < j + tempContinue; k++) {
                            sign.set(k, tempContinue - medal[i].getDay());
                        }
                        break;
                    }

                }
            }
        }
        return number;
    }
*/

    private static List<MedalType> computeMedalType(long signHistory) {
        List<MedalType> medalTypeSign = new ArrayList<MedalType>(31);
        for (int i = 0; i < 31; i++) {
            medalTypeSign.add(MedalType.NULL);
        }
        int count = 0;
        int result = 0;
        for (int i = 30; i >= 0; i--) {
            if ((signHistory & (1 << i)) > 0) {
                count++;
                if(i != 0){
                    continue;
                }
            }
            result = count / MedalType.GOLD.getDay();
            int withoutGold = count - result * MedalType.GOLD.getDay();
            if (result >= 1) {
                for (int j = i + count; j > i + withoutGold; j--) {
                    medalTypeSign.set(31 - j, MedalType.GOLD);
                }
            }

            MedalType leftType = MedalType.NULL;
            if (withoutGold >= MedalType.SILVER.getDay()) {
                leftType = MedalType.SILVER;
            } else if (withoutGold >= MedalType.BRONZE.getDay()) {
                leftType = MedalType.BRONZE;
            }

            for (int j = i + withoutGold; j > i + withoutGold - leftType.getDay(); j--) {
                medalTypeSign.set(31 - j, leftType);
            }

            count = 0;
        }
        return medalTypeSign;
    }


    //根据连续签到天数计算可获得多少枚金章，银章，铜章
    private static void medalNumber(int k, Map<MedalType, Integer> map) {
        int gold, silver, bronze = 0;
        gold = k / MedalType.GOLD.getDay();
        k = k - gold * MedalType.GOLD.getDay();
        silver = k / MedalType.SILVER.getDay();
        k = k - silver * MedalType.SILVER.getDay();
        bronze = k / MedalType.BRONZE.getDay();

        map.put(MedalType.GOLD, map.get(MedalType.GOLD) + gold);
        map.put(MedalType.SILVER, map.get(MedalType.SILVER) + silver);
        map.put(MedalType.BRONZE, map.get(MedalType.BRONZE) + bronze);
    }

    //给定从数据库中取出的签到历史，返回对应的金章，铜章，银章数目
    public static Map<MedalType, Integer> historyToMedal(long signHistory) {
        List<Integer> sign = computeMedal(signHistory);
        Map<MedalType, Integer> medalMap = new HashMap<MedalType, Integer>();
        medalMap.put(MedalType.GOLD, 0);
        medalMap.put(MedalType.SILVER, 0);
        medalMap.put(MedalType.BRONZE, 0);
        for (int i = 0; i < sign.size(); i++) {
            medalNumber(sign.get(i), medalMap);
        }
        return medalMap;
    }
    //根据签到历史和本次提交的兑换勋章请求，修改签到历史
    public static long modifyExchangeHistory(long exchangeHistory, List<MedalType> medalRequest){
        List<MedalType> count = computeMedalType(exchangeHistory);
        String strNumber = toFullBinaryString(exchangeHistory);
        List<Integer> number = new ArrayList<Integer>(31);
        for (int i = 0; i < 31; i++) {
            number.add(0);
        }
        for (int i = 0; i < 31; i++) {
            int k = 0;
            if (strNumber.charAt(i) == '1') {
                k = 1;
            }
            number.set(i, k);
        }
        for (MedalType medalType : medalRequest){
            for (int i = 0; i < 31; i++){
                if(count.get(i) == medalType){
                    for(int j = i; j < i + medalType.getDay(); j++){
                        count.set(j, MedalType.NULL);
                        number.set(j, 0);
                    }
                    break;
                }

            }
        }
        long result = 0;
        for(int i = 0; i < 31; i++){
            result = (result << 1) + number.get(i);
        }
        return result;
    }

    //enum枚举类型，表示四种不同的MedalType
    public enum MedalType {
        GOLD(7),
        SILVER(5),
        BRONZE(3),
        NULL(0);
        private final int day;
        MedalType(int day) {
            this.day = day;
        }
        public int getDay() {
            return day;
        }

    }

    public static void main(String[] args) {
        long history = 268421095;
        List<MedalType> medalRequest = new ArrayList<MedalType>();
        medalRequest.add(MedalType.BRONZE);
        long result = modifyExchangeHistory(history, medalRequest);
        System.out.println("result::::" + result);

    }

}
