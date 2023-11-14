package com.pddbend.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pddbend.shortlink.admin.dao.entity.UserDO;
import com.pddbend.shortlink.admin.dto.resp.UserRespDTO;

/**
 * @Author: pddbend
 * @Date: 2023-11-14-下午2:39
 * @Description: 用户接口层
 */
public interface UserService extends IService<UserDO> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
    UserRespDTO getUserByUsername(String username);
}
