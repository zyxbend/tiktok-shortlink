package com.pddbend.shortlink.admin.controller;

import cn.hutool.core.bean.BeanUtil;
import com.pddbend.shortlink.admin.common.convention.result.Result;
import com.pddbend.shortlink.admin.common.convention.result.Results;
import com.pddbend.shortlink.admin.dto.req.UserLoginRepDTO;
import com.pddbend.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.pddbend.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.pddbend.shortlink.admin.dto.resp.UserActualRespDTO;
import com.pddbend.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.pddbend.shortlink.admin.dto.resp.UserRespDTO;
import com.pddbend.shortlink.admin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: pddbend
 * @Date: 2023-11-14-下午2:42
 * @Description: 用户管理控制层
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据用户名查询用户脱敏信息
     */
    @GetMapping("/api/short-link/admin/v1/user/{username}")
    public Result<UserRespDTO> getUserByUsername(@PathVariable("username") String username) {
        return Results.success(userService.getUserByUsername(username));
    }

    /**
     * 根据用户名查询用户真实信息
     */
    @GetMapping("/api/short-link/admin/v1/actual/user/{username}")
    public Result<UserActualRespDTO> getUserAactualByUsername(@PathVariable("username") String username) {
        return Results.success(BeanUtil.toBean(userService.getUserByUsername(username), UserActualRespDTO.class));
    }

    /**
     * 查询用户名是否存在
     */
    @GetMapping("/api/short-link/admin/v1/user/has-username")
    public Result<Boolean> hasUserName(@RequestParam("username") String username) {
        return Results.success(userService.hasUserName(username));
    }

    /**
     * 用户注册
     */
    @PostMapping("/api/short-link/admin/v1/user")
    public Result<Void> register(@RequestBody UserRegisterReqDTO requestParam) {
        userService.register(requestParam);
        return Results.success();
    }

    /**
     * 用户修改
     */
    @PutMapping("/api/short-link/admin/v1/user")
    public Result<Void> update(@RequestBody UserUpdateReqDTO requestParam) {
        userService.update(requestParam);
        return Results.success();
    }

    /**
     * 用户登录
     */
    @PostMapping("/api/short-link/admin/v1/user/login")
    public Result<UserLoginRespDTO> login (@RequestBody UserLoginRepDTO requestParam) {
        return Results.success(userService.login(requestParam));
    }

    /**
     * 检查用户是否登陆
     * @param token 用户登陆 Token
     * @return true 已登陆 false 未登陆
     */
    @GetMapping("/api/short-link/admin/v1/user/check-login")
    public Result<Boolean> checkLogin(@RequestParam("token") String token, @RequestParam("username") String username) {
        return Results.success(userService.checkLogin(token, username));
    }

    @DeleteMapping("/api/short-link/admin/v1/user/logout")
    public Result<Void> logout(@RequestParam("token") String token, @RequestParam("username") String username) {
        userService.logout(token, username);
        return Results.success();
    }
}
