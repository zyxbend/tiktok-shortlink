package com.pddbend.shortlink.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pddbend.shortlink.project.dao.entity.ShortLinkDO;
import com.pddbend.shortlink.project.dto.req.ShortLinkRecycleBinPageReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkRecycleBinRecoverReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkRecycleBinRemoveReqDTO;
import com.pddbend.shortlink.project.dto.req.ShortLinkRecycleBinSaveReqDTO;
import com.pddbend.shortlink.project.dto.resp.ShortLinkPageRespDTO;

/**
 * @Author: pddbend
 * @Date: 2023-11-29-14:53
 * @Description: 回收站管理接口层
 */
public interface RecycleBinService extends IService<ShortLinkDO> {

    /**
     * 保存短链接到回收站
     * @param requestParam 保存短链接请求参数
     */
    void saveRecycleBin(ShortLinkRecycleBinSaveReqDTO requestParam);

    /**
     * 分页查询回收站内短链接
     * @param requestParam 查询请求参数
     * @return 短链接分页列表
     */
    IPage<ShortLinkPageRespDTO> pageRecycleBin(ShortLinkRecycleBinPageReqDTO requestParam);

    /**
     * 从回收站恢复短链接
     * @param requestParam 恢复请求参数
     */
    void recoverRecycleBin(ShortLinkRecycleBinRecoverReqDTO requestParam);


    /**
     * 从回收站删除短链接
     * @param requestParam 删除请求参数
     */
    void removeRecycleBin(ShortLinkRecycleBinRemoveReqDTO requestParam);
}
