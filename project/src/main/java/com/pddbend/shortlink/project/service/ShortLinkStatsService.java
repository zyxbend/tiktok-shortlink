package com.pddbend.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pddbend.shortlink.project.dto.req.ShortLinkStatsAccessRecordReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkStatsReqDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkStatsAccessRecordRespDTO;
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

    /**
     * 查询单个短链接指定时间内访问记录监控数据
     * @param requestParam 请求参数
     * @return 短链接访问记录监控数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> shortLinkStatsAccessRecord(ShortLinkStatsAccessRecordReqDTO requestParam);
}