package com.pddbend.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pddbend.shortlink.project.dto.req.ShortLinkGroupStatsAccessRecordReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkGroupStatsReqDTO;
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

     /**
     * 获取分组短链接监控数据
     *
     * @param requestParam 获取分组短链接监控数据入参
     * @return 分组短链接监控数据
     */
    ShortLinkStatsRespDTO groupShortLinkStats(ShortLinkGroupStatsReqDTO requestParam);

     /**
     * 访问分组短链接指定时间内访问记录监控数据
     *
     * @param requestParam 获取分组短链接监控访问记录数据入参
     * @return 分组访问记录监控数据
     */
    IPage<ShortLinkStatsAccessRecordRespDTO> groupShortLinkStatsAccessRecord(ShortLinkGroupStatsAccessRecordReqDTO requestParam);
}
