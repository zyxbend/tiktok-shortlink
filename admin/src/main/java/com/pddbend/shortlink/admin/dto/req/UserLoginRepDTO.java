package com.pddbend.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-20-下午6:43
 * @Description: 用户登陆请求参数
 */

@Data
public class UserLoginRepDTO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
