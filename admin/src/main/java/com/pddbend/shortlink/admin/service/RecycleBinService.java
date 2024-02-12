package com.pddbend.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pddbend.shortlink.admin.common.convention.result.Result;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.pddbend.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;

/**
 * @Author: pddbend
 * @Date: 2023-11-29-20:52
 * @Description: 回收站管理接口层
 */

public interface RecycleBinService {
    /**
     * 分页查询回收站短链接
     *
     * @param requestParam 请求参数
     * @return 返回参数包装
     */
    Result<Page<ShortLinkPageRespDTO>> pageRecycleBinShortLink(ShortLinkRecycleBinPageReqDTO requestParam);
}
