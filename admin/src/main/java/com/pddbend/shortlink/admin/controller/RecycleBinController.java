package com.pddbend.shortlink.admin.controller;

import com.pddbend.shortlink.admin.remote.dto.ShortLinkRemoteService;
import com.pddbend.shortlink.admin.remote.dto.req.RecycleBinSaveReqDTO;
import com.pddbend.shortlink.project.common.convention.result.Result;
import com.pddbend.shortlink.project.common.convention.result.Results;
import lombok.RequiredArgsConstructor;
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

     ShortLinkRemoteService shortLinkRemoteService = new ShortLinkRemoteService() {
    };

    /**
     * 短链接移至回收站
     */
    @PostMapping("/api/short-link/admin/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        shortLinkRemoteService.saveRecycleBin(requestParam);
        return Results.success();
    }
}
