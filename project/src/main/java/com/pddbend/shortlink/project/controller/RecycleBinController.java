package com.pddbend.shortlink.project.controller;

import com.pddbend.shortlink.project.common.convention.result.Result;
import com.pddbend.shortlink.project.common.convention.result.Results;
import com.pddbend.shortlink.project.dto.req.RecycleBinSaveReqDTO;
import com.pddbend.shortlink.project.service.RecycleBinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pddbend
 * @Date: 2023-11-29-14:49
 * @Description: 回收站控制器
 */

@RestController
@RequiredArgsConstructor
public class RecycleBinController {

    private final RecycleBinService recycleBinService;

    /**
     * 短链接移至回收站
     */
    @PostMapping("/api/short-link/v1/recycle-bin/save")
    public Result<Void> saveRecycleBin(@RequestBody RecycleBinSaveReqDTO requestParam) {
        recycleBinService.saveRecycleBin(requestParam);
        return Results.success();
    }
}
