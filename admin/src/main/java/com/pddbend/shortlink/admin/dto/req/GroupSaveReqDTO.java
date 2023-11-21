package com.pddbend.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午10:26
 * @Description: 短链接新建分组请求DTO
 */
@Data
public class GroupSaveReqDTO {

    /**
     * 分组名称
     */
    private String name;
}
