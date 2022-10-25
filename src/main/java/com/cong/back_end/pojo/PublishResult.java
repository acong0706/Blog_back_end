package com.cong.back_end.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Cong
 * @Create_time 2022-10-24 下午 07:46
 * @Project_name back_end
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublishResult {
    private int id;
    private boolean result;
}
