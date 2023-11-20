package com.pddbend.shortlink.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pddbend.shortlink.admin.common.convention.exception.ClientException;
import com.pddbend.shortlink.admin.common.enums.UserErrorCodeEnum;
import com.pddbend.shortlink.admin.dao.entity.UserDO;
import com.pddbend.shortlink.admin.dao.mapper.UserMapper;
import com.pddbend.shortlink.admin.dto.req.UserLoginRepDTO;
import com.pddbend.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.pddbend.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.pddbend.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.pddbend.shortlink.admin.dto.resp.UserRespDTO;
import com.pddbend.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.pddbend.shortlink.admin.common.constant.RedisCacheConstant.LOCK_USER_REGISTER_KEY;

/**
 * @Author: pddbend
 * @Date: 2023-11-14-下午2:40
 * @Description: 用户接口实现曾
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {

    private final RBloomFilter<String> userRegisterCachePenetrationBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户返回实体
     */
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

    /**
     * 查询用户名是否存在
     *
     * @param username 用户名
     * @return 用户名true 存在 false 不存在
     */
    @Override
    public Boolean hasUserName(String username) {
        return !userRegisterCachePenetrationBloomFilter.contains(username);
    }

    /**
     * 用户注册
     *
     * @param requestParam 用户注册请求参数
     */
    @Override
    public void register(UserRegisterReqDTO requestParam) {
        if (!hasUserName(requestParam.getUsername())) {
            throw new ClientException(UserErrorCodeEnum.USER_NAME_EXISTED);
        }
        RLock lock = redissonClient.getLock(LOCK_USER_REGISTER_KEY + requestParam.getUsername());
        try {
            if (lock.tryLock()) {
                int insered = baseMapper.insert(BeanUtil.toBean(requestParam, UserDO.class));
                if (insered < 1) {
                    throw new ClientException(UserErrorCodeEnum.USER_SAVE_ERROR);
                }
                userRegisterCachePenetrationBloomFilter.add(requestParam.getUsername());
                return;
            }
            throw new ClientException(UserErrorCodeEnum.USER_EXISTED);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 用户修改 TODO 验证当前用户是否有权限修改
     *
     * @param requestParam 用户修改请求参数
     */
    @Override
    public void update(UserUpdateReqDTO requestParam) {
        // TODO 验证当前用户是否有权限修改
        LambdaQueryWrapper<UserDO> updateWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername());
        baseMapper.update(BeanUtil.toBean(requestParam, UserDO.class), updateWrapper);
    }


    /**
     * 用户登陆
     *
     * @param requestParam 用户登陆请求参数
     * @return 用户登陆返回参数
     */
    @Override
    public UserLoginRespDTO login(UserLoginRepDTO requestParam) {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, requestParam.getUsername())
                .eq(UserDO::getPassword, requestParam.getPassword())
                .eq(UserDO::getDelFlag, 0);
        UserDO userDO = baseMapper.selectOne(queryWrapper);
        if (userDO == null) {
            throw new ClientException(UserErrorCodeEnum.USER_NOT_EXIST);
        }
        Boolean hasLogined = stringRedisTemplate.hasKey("login_" + requestParam.getUsername());
        if (hasLogined != null && hasLogined) {
            throw new ClientException(UserErrorCodeEnum.USER_LOGINED);
        }
        /*
          Hash
          Key : login_用户名
          Value :
             Key : token
             Value : 用户信息（JSON字符串）
          过期时间 : 30分钟
         */
        String uuid = UUID.randomUUID().toString();
        stringRedisTemplate.opsForHash().put("login_" + requestParam.getUsername(), uuid, JSON.toJSONString(userDO));
        stringRedisTemplate.expire("login_" + requestParam.getUsername(), 30L, TimeUnit.DAYS);
        return new UserLoginRespDTO(uuid);
    }


    /**
     * 检查用户是否登陆
     *
     * @param token 用户登陆token
     * @return true 已登陆 false 未登陆
     */
    @Override
    public Boolean checkLogin(String token, String username) {
        return stringRedisTemplate.opsForHash().get("login_" + username, token) != null;
    }

    /**
     * 用户退出登陆
     * @param token 用户登陆 Token
     * @param username 用户名
     */
    @Override
    public void logout(String token, String username) {
        if (checkLogin(token, username)) {
            stringRedisTemplate.delete("login_" + username);
            return;
        }
        throw new ClientException(UserErrorCodeEnum.USER_NOT_LOGINED);

    }


}
