package com.cong.back_end.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Cong
 * @Create_time 2022-10-28 下午 09:31
 * @Project_name back_end
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessNum {
    private int id;
    private String dateTime;
    private int num;
}
