package com.cong.back_end.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Cong
 * @Create_time 2022-10-17 下午 06:07
 * @Project_name back_end
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String Token;
    private String refreshToken;
}
