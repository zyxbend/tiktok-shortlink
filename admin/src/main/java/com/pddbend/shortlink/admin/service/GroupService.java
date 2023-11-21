package com.pddbend.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pddbend.shortlink.admin.dao.entity.GroupDO;
import com.pddbend.shortlink.admin.dto.resp.GroupRespDTO;

import java.util.List;

/**
 * @Author: pddbend
 * @Date: 2023-11-21-上午9:56
 * @Description: 短链接分组接口层
 */
public interface GroupService extends IService<GroupDO> {

    /**
     * 新增短链接分组
     * @param groupName 短链接分组名称
     */
    void saveGroup(String groupName);

    List<GroupRespDTO> listGroup();
}
