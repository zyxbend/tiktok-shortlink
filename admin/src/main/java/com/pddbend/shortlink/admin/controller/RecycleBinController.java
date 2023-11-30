package com.pddbend.shortlink.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pddbend.shortlink.admin.common.convention.result.Result;
import com.pddbend.shortlink.admin.common.convention.result.Results;
import com.pddbend.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkRecycleBinSaveReqDTO;
import com.pddbend.shortlink.admin.remote.dto.resp.ShortLinkPageRespDTO;
import com.pddbend.shortlink.admin.service.RecycleBinService;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkRecycleBinRecoverReqDTO;
import com.pddbend.shortlink.admin.remote.dto.req.ShortLinkRecycleBinRemoveReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pddbend
 * @Date: 2023-11-29-15:03
 * @Description: 回收站控制层
 */
@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 短链接移至回收站
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody ShortLinkRecycleBinSaveReqDTO requestParam) {
        shortLinkRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * 分页查询短链接
     */
    @GetMapping("/api/short-link/admin/v1/recycle-bin/page")
    public Result<IPage<ShortLinkPageRespDTO>> pageRecycleBin(ShortLinkRecycleBinPageReqDTO requestParam) {
        return recycleBinService.pageRecycleBinShortLink(requestParam);
    }

    /**
     * 短链接从回收站恢复
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/recover")
    public Result<Void> recoverRecycleBin(@RequestBody ShortLinkRecycleBinRecoverReqDTO requestParam) {
        shortLinkRemoteService.recoverRecycleBin(requestParam);
        return Results.success();
    }

    /**
     * 短链接从回收站删除
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/remove")
    public Result<Void> removeRecycleBin(@RequestBody ShortLinkRecycleBinRemoveReqDTO requestParam) {
        shortLinkRemoteService.removeRecycleBin(requestParam);
        return Results.success();
    }
}
