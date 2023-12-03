package com.pddbend.shortlink.admin.controller;

import com.pddbend.shortlink.admin.common.convention.result.Result;
import com.pddbend.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkStatsReqDTO;
import com.pddbend.shortlink.admin.remote.dto.resp.ShortLinkStatsRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pddbend
 * @Date: 2023-12-03-19:37
 * @Description: 短链接监控控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkStatsController {

    /**
     * 后续重构为 SpringCloud Feign 调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 访问单个短链接指定时间内监控数据
     */
    @GetMapping("/api/short-link/admin/v1/stats")
    public Result<ShortLinkStatsRespDTO> shortLinkStats(ShortLinkStatsReqDTO requestParam) {
        return shortLinkRemoteService.oneShortLinkStats(requestParam);
    }
}
