package com.pddbend.shortlink.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pddbend.shortlink.project.dao.entity.ShortLinkDO;
import com.pddbend.shortlink.project.dto.req.ShortLinkPageReqDTO;

/**
 * @Author: pddbend
 * @Date: 2023-11-22-上午9:53
 * @Description: 短链接持久层
 */
public interface ShortLinkMapper extends BaseMapper<ShortLinkDO> {
    /**
     * 分页统计短链接
     */
    IPage<ShortLinkDO> pageLink(ShortLinkPageReqDTO requestParam);
}
