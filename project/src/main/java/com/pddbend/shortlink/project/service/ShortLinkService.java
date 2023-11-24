package com.pddbend.shortlink.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pddbend.shortlink.project.dao.entity.ShortLinkDO;
import com.pddbend.shortlink.project.dto.req.ShortLinkCreateReqDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkCreateRespDTO;

/**
 * @Author: pddbend
 * @Date: 2023-11-22-上午9:55
 * @Description: 短链接接口层
 */
public interface ShortLinkService extends IService<ShortLinkDO> {

    /**
     * 创建短链接
     * @param requestParam 请求参数
     * @return 短链接响应参数
     */
    ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO requestParam);
}