package com.pddbend.shortlink.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pddbend.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.pddbend.shortlink.admin.common.convention.exception.ClientException;
import com.pddbend.shortlink.admin.dao.entity.UserDO;
import com.pddbend.shortlink.admin.dao.mapper.UserMapper;
import com.pddbend.shortlink.admin.dto.resp.UserRespDTO;
import com.pddbend.shortlink.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author: pddbend
 * @Date: 2023-11-14-下午2:40
 * @Description: 用户接口实现曾
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NOT_EXIST);
        }
        UserRespDTO result = new UserRespDTO();
        BeanUtils.copyProperties(userDO, result);
        return result;
    }
}
