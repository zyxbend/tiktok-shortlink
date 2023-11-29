package com.pddbend.shortlink.admin.remote.dto.req;

import lombok.Data;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
 * @Author: pddbend
 * @Date: 2023-11-29-20:51
 * @Description: 短链接回收站分页查询请求参数
 */
@Data
public class ShortLinkRecycleBinPageReqDTO extends Page {

    /**
     * 分组标识列表
     */
    private List<String> gidList;
}
