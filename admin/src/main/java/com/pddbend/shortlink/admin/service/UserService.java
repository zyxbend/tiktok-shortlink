package com.pddbend.shortlink.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pddbend.shortlink.admin.dao.entity.UserDO;
import com.pddbend.shortlink.admin.dto.req.UserLoginRepDTO;
import com.pddbend.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.pddbend.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.pddbend.shortlink.admin.dto.resp.UserLoginRespDTO;
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

    /**
     * 查询用户名是否存在
     *
     * @param username
     * @return true 存在 false 不存在
     */
    Boolean hasUserName(String username);


    /**
     * 用户注册
     *
     * @param requestParam 用户注册请求参数
     */
    void register(UserRegisterReqDTO requestParam);

    /**
     * 用户修改
     *
     * @param requestParam 用户修改请求参数
     */
    void update(UserUpdateReqDTO requestParam);


    /**
     * 用户登陆
     * @param requestParam 用户登陆请求参数
     * @return 用户登陆返回参数
     */
    UserLoginRespDTO login(UserLoginRepDTO requestParam);

    /**
     * 检查用户是否登陆
     * @param token 用户登陆 Token
     * @return true 已登陆 false 未登陆
     */
    Boolean checkLogin(String token, String username);

    /**
     * 用户退出登陆
     * @param token 用户登陆 Token
     * @param username 用户名
     */
    void logout(String token, String username);
}
