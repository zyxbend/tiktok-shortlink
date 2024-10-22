package com.pddbend.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pddbend.shortlink.project.dao.entity.ShortLinkDO;
import com.pddbend.shortlink.project.dto.biz.ShortLinkStatsRecordDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkBatchCreateReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkPageReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkUpdateReqDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkBatchCreateRespDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkPageRespDTO;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.util.List;

/**
 * @Author: pddbend
 * @Date: 2023-11-22-上午9:55
 * @Description: 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     *
     * @param requestParam 请求参数
     * @return 短链接响应参数
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);

    /**
     * 批量创建短链接
     *
     * @param requestParam 批量创建短链接请求参数
     * @return 批量创建短链接返回参数
     */
    ShortLinkBatchCreateRespDTO batchCreateShortLink(ShortLinkBatchCreateReqDTO requestParam);

    /**
     * 分页查询短链接
     *
     * @param requestParam 请求参数
     * @return 短链接响应参数
     */
    IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO requestParam);

    /**
     * 查询短链接分组内数量
     *
     * @param requestParam 请求参数
     * @return 短链接响应参数
     */
    List<ShortLinkGroupCountQueryRespDTO> listGroupShortLinkCount(List<String> requestParam);

    /**
     * 更新短链接
     *
     * @param requestParam 请求参数
     */
    void updateShortLink(ShortLinkUpdateReqDTO requestParam);

    /**
     * 短链接跳转原始链接
     *
     * @param shortUri 短链接后缀
     * @param request  HTTP请求
     * @param response HTTP响应
     */
    void restoreUrl(String shortUri, ServletRequest request, ServletResponse response);

    /**
     * 短链接统计
     *
     * @param fullShortUrl         完整短链接
     * @param gid                  分组标识
     * @param shortLinkStatsRecord 短链接统计实体参数
     */
    void shortLinkStats(String fullShortUrl, String gid, ShortLinkStatsRecordDTO shortLinkStatsRecord);
}
