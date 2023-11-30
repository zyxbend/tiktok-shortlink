package com.pddbend.shortlink.admin.remote.dto.req;

import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-30-14:21
 * @Description: 回收站撤销删除请求参数
 */
@Data
public class ShortLinkRecycleBinRecoverReqDTO {
    /**
     * 分组标识
     */
    private String gid;
    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
