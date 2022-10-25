package com.cong.back_end.util;

/**
 * @Author Cong
 * @Create_time 2022-10-24 下午 05:09
 * @Project_name back_end
 */
public class LabelChange {
    
    // 数据库中存储的格式
    public static String labelArrayToString(String[] labelArray) {
        String labelString = labelArray[0].split("=")[1];
        for (int i = 1; i < labelArray.length; i++) {
            labelString += "," + labelArray[i].split("=")[1];
        }
        return labelString;
    }
    
    public static String[] labelStringToArray(String labelString) {
        return labelString.split(",");
    }
}
