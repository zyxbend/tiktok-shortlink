package com.pddbend.shortlink.project.dto.req;

import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-29-14:51
 * @Description: 回收站删除短链接请求参数
 */
@Data
public class ShortLinkRecycleBinRemoveReqDTO {
    /**
     * 分组标识
     */
    private String gid;
    /**
     * 完整短链接
     */
    private String fullShortUrl;
}
