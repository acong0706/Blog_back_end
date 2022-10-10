package com.cong.back_end.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Cong
 * @Create_time 2022-10-10 下午 03:06
 * @Project_name back_end
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponse<T> {
    
    public static final String SUCCESS_MSG = "邮箱发送成功";
    public static final String FAILURE_MSG = "邮箱发送失败";
    public static final Integer SUCCESS_CODE = 200;
    public static final Integer FAILURE_CODE = 500;
    
    private Integer code;
    
    private String msg;
    private T data;
    
    public RestResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }
    
    public static RestResponse buildFailureResp(Exception e) {
        return new RestResponse(RestResponse.FAILURE_CODE, e.getMessage());
    }
    
    public static RestResponse buildSuccessResp(Object data) {
        return new RestResponse(RestResponse.SUCCESS_CODE, RestResponse.SUCCESS_MSG, data);
    }
}
