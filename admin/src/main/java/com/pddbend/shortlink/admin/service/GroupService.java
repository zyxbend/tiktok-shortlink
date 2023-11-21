package com.pddbend.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pddbend.shortlink.admin.dao.entity.GroupDO;
import com.pddbend.shortlink.admin.dto.req.GroupUpdateReqDTO;
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

    /**
     * 查询用户所有短链接分组
     * @return 用户短链接分组列表
     */
    List<GroupRespDTO> listGroup();

    /**
     * 更新分组
     * @param requestParam 分组信息
     * @return 更新后的分组信息
     */
    void updateGroup(GroupUpdateReqDTO requestParam);



}
