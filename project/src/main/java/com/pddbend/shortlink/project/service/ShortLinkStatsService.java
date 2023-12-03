package com.pddbend.shortlink.project.service;

import com.pddbend.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkStatsRespDTO;

/**
 * @Author: pddbend
 * @Date: 2023-12-03-18:52
 * @Description: 短链接监控接口层
 */
public interface ShortLinkStatsService  {

    /**
     * 查询单个短链接指定时间内监控数据
     * @param requestParam 请求参数
     * @return 短链接监控数据
     */
    ShortLinkStatsRespDTO oneShortLinkStats(ShortLinkStatsReqDTO requestParam);
}
