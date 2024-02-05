package com.pddbend.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pddbend.shortlink.admin.common.convention.result.Result;
import com.pddbend.shortlink.admin.common.convention.result.Results;
import com.pddbend.shortlink.admin.dto.req.ShortLinkBatchCreateReqDTO;
import com.pddbend.shortlink.admin.dto.resp.ShortLinkBaseInfoRespDTO;
import com.pddbend.shortlink.admin.dto.resp.ShortLinkBatchCreateRespDTO;
import com.pddbend.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkCreateReqDTO;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkPageReqDTO;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkUpdateReqDTO;
import com.pddbend.shortlink.admin.remote.dto.resp.ShortLinkCreateRespDTO;
import com.pddbend.shortlink.admin.remote.dto.resp.ShortLinkGroupCountQueryRespDTO;
import com.pddbend.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.pddbend.shortlink.admin.toolkit.EasyExcelWebUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: pddbend
 * @Date: 2023-11-26-16:49
 * @Description: 短链接后管控制层
 */
@RestController
public class ShortLinkController {
    /**
     * 短链接中台远程调用服务
     * TODO: 后续重构为Spring Cloud Feign调用
     */
    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return shortLinkRemoteService.createShortLink(requestParam);
    }

    /**
     * 批量创建短链接
     */
    @SneakyThrows
    @PostMapping("/api/short-link/admin/v1/create/batch")
    public void batchCreateShortLink(@RequestBody ShortLinkBatchCreateReqDTO requestParam, HttpServletResponse response) {
        Result<ShortLinkBatchCreateRespDTO> shortLinkBatchCreateRespDTOResult = shortLinkRemoteService.batchCreateShortLink(requestParam);
        if (shortLinkBatchCreateRespDTOResult.isSuccess()) {
            List<ShortLinkBaseInfoRespDTO> baseLinkInfos = shortLinkBatchCreateRespDTOResult.getData().getBaseLinkInfos();
            EasyExcelWebUtil.write(response, "批量创建短链接-SaaS短链接系统", ShortLinkBaseInfoRespDTO.class, baseLinkInfos);
        }
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/admin/v1/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageShortLink(ShortLinkPageReqDTO requestParam) {
        return shortLinkRemoteService.pageShortLink(requestParam);
    }

    /**
     * 查询短链接分组内数量
     */
    @GetMapping("/api/short-link/admin/v1/count")
    public Result<List<ShortLinkGroupCountQueryRespDTO>> listGroupShortLinkCount(@RequestParam("requestParam") List<String> requestParam) {
        return shortLinkRemoteService.listGroupShortLinkCount(requestParam);
    }

    /**
     * 更新短链接
     */
    @PostMapping("/api/short-link/admin/v1/update")
    public Result<Void> updateShortLink(@RequestBody ShortLinkUpdateReqDTO requestParam) {
        shortLinkRemoteService.updateShortLink(requestParam);
        return Results.success();
    }
}
