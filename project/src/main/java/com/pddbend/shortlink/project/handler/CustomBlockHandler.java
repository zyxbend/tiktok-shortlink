package com.pddbend.shortlink.project.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.pddbend.shortlink.project.common.convention.result.Result;
import com.pddbend.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * @Author: pddbend
 * @Date: 2024-02-12-19:15
 * @Description: 自定义流控策略
 */
public class CustomBlockHandler {

    public static Result<ShortLinkCreateRespDTO> createShortLinkBlockHandlerMethod(ShortLinkCreateReqDTO requestParam, BlockException exception) {
        return new Result<ShortLinkCreateRespDTO>().setCode("B100000").setMessage("当前访问网站人数过多，请稍后再试...");
    }
}
