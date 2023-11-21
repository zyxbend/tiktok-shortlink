package com.pddbend.shortlink.admin.dto.req;

import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午10:26
 * @Description: 短链接更新分组请求DTO
 */
@Data
public class GroupSortReqDTO {

    /**
     * 分组ID
     */
    private String gid;

    /**
     * 分组排序
     */
    private Integer sortOrder;
}
