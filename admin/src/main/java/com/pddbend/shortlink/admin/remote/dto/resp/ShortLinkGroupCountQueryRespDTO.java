package com.pddbend.shortlink.admin.remote.dto.resp;

import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-26-20:30
 * @Description: 短链接分组数量查询返回参数
 */
@Data
public class ShortLinkGroupCountQueryRespDTO {

    /**
     * 分组标识
     */
    private String gid;

    /**
     * 短链接数量
     */
    private Integer shortLinkCount;
}