package com.pddbend.shortlink.project.controller;

import com.pddbend.shortlink.project.common.convention.result.Result;
import com.pddbend.shortlink.project.common.convention.result.Results;
import com.pddbend.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.pddbend.shortlink.project.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: pddbend
 * @Date: 2023-11-22-上午10:04
 * @Description:  短链接控制层
 */
@RestController
@RequiredArgsConstructor
public class ShortLinkController {


    private final ShortLinkService shortLinkService;

    /**
     * 创建短链接
     */
    @PostMapping("/api/short-link/admin/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {

        return Results.success(shortLinkService.createShortLink(requestParam));
    }
}

