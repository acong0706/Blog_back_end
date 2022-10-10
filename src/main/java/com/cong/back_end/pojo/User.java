package com.cong.back_end.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Cong
 * @Create_time 2022-10-08 下午 02:57
 * @Project_name back_end
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer account;
    private String pwd;
}
