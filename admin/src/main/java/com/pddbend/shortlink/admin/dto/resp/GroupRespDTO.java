package com.pddbend.shortlink.admin.dto.resp;

import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午10:45
 * @Description: 短链接分组返回参数响应
 */
@Data
public class GroupRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 分组名称
     */
    private String name;


    /**
     * 分组排序
     */
    private Integer sortOrder;
}
