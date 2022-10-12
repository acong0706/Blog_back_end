package com.cong.back_end.util;

import java.util.Arrays;

/**
 * @Author Cong
 * @Create_time 2022-10-12 下午 07:30
 * @Project_name back_end
 */
public class AccountGeneration {
    // 根据id生成account，大概率是无用功。
    public static String getAccountById(int id) {
        if (Integer.toString(id).length() > 4) {
            String num = Integer.toString(id);
            char[] temp = num.toCharArray();
            int length = temp.length - 5;
            for (;length >= 0;length--) {
                if (Character.getNumericValue(temp[length]) == 9) {
                    temp[length] = '0';
                } else {
                    temp[length] = (char)(Character.getNumericValue(temp[length]) + 49);
                    break;
                }
            }
            num = Arrays.toString(temp).replaceAll("[\\[\\]\\s,]", "");
            if(length < 0) {
                return "1" + num;
            } else {
                return num;
            }
        } else {
            return Integer.toString(id + 10000);
        }
    }
}
