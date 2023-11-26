package com.pddbend.shortlink.admin.remote.dto.req;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pddbend.shortlink.project.dao.entity.ShortLinkDO;
import lombok.Data;

/**
 * @Author: pddbend
 * @Date: 2023-11-24-下午2:18
 * @Description: 分页查询短链接请求参数
 */
@Data
public class ShortLinkPageReqDTO extends Page<ShortLinkDO> {

    /**
     * 分组标识
     */
    private String gid;

//    /**
//     * 排序标识
//     */
//    private String orderTag;
}